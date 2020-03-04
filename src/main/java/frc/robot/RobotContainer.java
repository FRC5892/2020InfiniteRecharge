/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.POVTrigger;
import frc.robot.commands.AimAndShoot;
import frc.robot.commands.BackUpAndAimAndShoot;
import frc.robot.commands.autons.ShootAndMoveTowardsTrench;
import frc.robot.commands.autons.ShootFromInFrontOfTrench;
import frc.robot.commands.autons.UltraSimpleAuton;
import frc.robot.subsystems.accumulator.AccumulatorSubsystem;
import frc.robot.subsystems.climb.ClimbSubsystem;
import frc.robot.subsystems.drive.DriveSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.intake.JoystickIntakeCommand;
import frc.robot.subsystems.limelight.Limelight;
import frc.robot.subsystems.limelight.PeerThroughLimelight;
import frc.robot.subsystems.shooter.ShooterSubsystem;
import frc.robot.subsystems.wheel.SetWheelPiston;
import frc.robot.subsystems.wheel.WheelCommandGroup;
import frc.robot.subsystems.wheel.WheelPositionControl;
import frc.robot.subsystems.wheel.WheelRotationControl;
import frc.robot.subsystems.wheel.WheelSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public final RobotMap map;

  public final Joystick pilot;
  public final Joystick copilot;

  // The robot's subsystems and commands are defined here...
  public final DriveSubsystem drive;
  public final IntakeSubsystem intake;
  public final AccumulatorSubsystem accumulator;
  public final ShooterSubsystem shooter;
  public final WheelSubsystem wheel;
  public final ClimbSubsystem climb;

  public final Limelight limelight;

  private final SendableChooser<Command> autonChooser = new SendableChooser<>();
  private final NetworkTableEntry pressure;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    try {
      map = new ObjectMapper().readValue(new File("/home/lvuser/deploy/RobotMap.json"), RobotMap.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    pilot = new Joystick(0);
    copilot = new Joystick(1);

    drive = new DriveSubsystem(map, this);
    intake = new IntakeSubsystem(map, this);
    accumulator = new AccumulatorSubsystem(map, this);
    shooter = new ShooterSubsystem(map, this);
    wheel = new WheelSubsystem(map, this);
    climb = new ClimbSubsystem(map, this);

    intake.setDefaultCommand(new JoystickIntakeCommand(intake, accumulator));

    limelight = new Limelight();

    // Configure the button bindings
    configureButtonBindings();

    // Set up autonomous options and Shuffleboard
    autonChooser.setDefaultOption("Simple Auto (Fwd)", new UltraSimpleAuton(this, -0.6));
    autonChooser.addOption("Simple Auto (Rev)", new UltraSimpleAuton(this, 0.6));
    autonChooser.addOption("Shoot & Go Trench", new ShootAndMoveTowardsTrench(this));
    autonChooser.addOption("Shoot By Trench & Go", new ShootFromInFrontOfTrench(this));
    var tab = Shuffleboard.getTab("Driver Dashboard");
    tab.add("Auto", autonChooser).withPosition(0, 0).withSize(2, 1);
    pressure = tab.add("Pressure", 0).withWidget(BuiltInWidgets.kNumberBar)
        .withProperties(Map.of("Min", 0, "Max", 1)).withPosition(2, 0).withSize(2, 1).getEntry();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(pilot, 5).whileActiveOnce(new PeerThroughLimelight(limelight));
    new JoystickButton(pilot, 1).whenActive(new AimAndShoot(this, 50));
    new POVTrigger(pilot, 180).whenActive(new BackUpAndAimAndShoot(this));
    new POVTrigger(copilot, 0).whenActive(new SetWheelPiston(wheel, true));
    new POVTrigger(copilot, 180).whenActive(new SetWheelPiston(wheel, false));
    new JoystickButton(copilot, 1).whenActive(new WheelCommandGroup(drive, wheel, new WheelRotationControl(wheel, .5)));
    new JoystickButton(copilot, 2).whenActive(new WheelCommandGroup(drive, wheel, new WheelPositionControl(wheel, .5)));
    new JoystickButton(pilot, 9).and(new JoystickButton(pilot, 10))
        .whenActive(new InstantCommand(() -> CommandScheduler.getInstance().cancelAll()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autonChooser.getSelected();
  }

  public void updatePressure(double value) {
    pressure.setNumber(value);
  }
}

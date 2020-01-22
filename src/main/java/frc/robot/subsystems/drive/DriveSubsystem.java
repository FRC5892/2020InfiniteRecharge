package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.MotorSpecs;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class DriveSubsystem extends SubsystemBase {
    public final RobotContainer container;
    private final DifferentialDrive drive;

    public DriveSubsystem(RobotMap map, RobotContainer container) {
        this.container = container;
        drive = new DifferentialDrive(MotorSpecs.makeSpeedControllers(map.leftDrive),
                MotorSpecs.makeSpeedControllers(map.rightDrive));
        addChild("Drive Train", drive);

        setDefaultCommand(new JoystickDriveCommand(this));
    }

    public void arcadeDrive(double xSpeed, double zRotation) {
        drive.arcadeDrive(xSpeed, zRotation);
    }
}
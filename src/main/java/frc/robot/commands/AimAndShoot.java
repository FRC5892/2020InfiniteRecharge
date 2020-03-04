package frc.robot.commands;

import frc.built_groups.BuiltParallel;
import frc.built_groups.BuiltSequence;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drive.VisionAlignCommand;
import frc.robot.subsystems.intake.SoloJoystickIntake;
import frc.robot.subsystems.shooter.ResetHood;
import frc.robot.subsystems.shooter.RunShooter;

public class AimAndShoot extends BuiltParallel {
    public AimAndShoot(RobotContainer container, double hoodSetpoint) {
        super(p -> {
            p.add(new VisionAlignCommand(container.drive, container.limelight));
            p.add(new SoloJoystickIntake(container.intake));
            p.add(new BuiltSequence(s -> {
                s.add(new ResetHood(container.shooter));
                s.add(new RunShooter(container.accumulator, container.shooter, 4000, hoodSetpoint));
            }));
        });
    }
}
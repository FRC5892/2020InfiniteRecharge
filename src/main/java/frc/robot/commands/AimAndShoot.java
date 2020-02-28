package frc.robot.commands;

import frc.HoldSubsystems;
import frc.built_groups.BuiltDeadline;
import frc.built_groups.BuiltRace;
import frc.built_groups.BuiltSequence;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drive.VisionAlignCommand;
import frc.robot.subsystems.shooter.ResetHood;
import frc.robot.subsystems.shooter.RunShooter;

public class AimAndShoot extends BuiltDeadline {
    public AimAndShoot(RobotContainer container, int hoodSetpoint) {
        super(new BuiltRace(r -> {
            r.add(new VisionAlignCommand(container.drive));
            r.add(new BuiltSequence(s -> {
                //s.add(new ResetHood(container.shooter));
                s.add(new RunShooter(container.accumulator, container.shooter, 4000, 0));
            }));
        }), d -> {
            d.add(new HoldSubsystems(container.intake));
        });
    }
}
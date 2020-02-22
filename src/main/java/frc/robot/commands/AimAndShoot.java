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
    public AimAndShoot(RobotContainer container) {
        super(new BuiltSequence(s -> {
            s.add(new ResetHood(container.shooter));
            s.add(new BuiltRace(r -> {
                r.add(new RunShooter(container.accumulator, container.shooter, 130000, 1000));
                r.add(new VisionAlignCommand(container.drive));
            }));
        }), d -> {
            d.add(new HoldSubsystems(container.intake));
        });
    }
}
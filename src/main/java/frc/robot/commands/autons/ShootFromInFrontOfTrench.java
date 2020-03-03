package frc.robot.commands.autons;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.built_groups.BuiltDeadline;
import frc.built_groups.BuiltParallel;
import frc.built_groups.BuiltSequence;
import frc.robot.RobotContainer;
import frc.robot.commands.AimAndShoot;
import frc.robot.subsystems.drive.AutoTankDrive;
import frc.robot.subsystems.drive.ResetEncoders;
import frc.robot.subsystems.intake.AutoIntake;
import frc.robot.subsystems.shooter.SuperHoodReset;

public class ShootFromInFrontOfTrench extends BuiltSequence {
    public ShootFromInFrontOfTrench(RobotContainer container) {
        super(s -> {
            s.add(new AimAndShoot(container, false).withTimeout(5));
            s.add(new BuiltParallel(p -> {
                p.add(new SuperHoodReset(container.shooter));
                p.add(new BuiltSequence(s1 -> {
                    s1.add(new ResetEncoders(container.drive));
                    s1.add(new AutoTankDrive(container.drive, 0.3, -0.3, 13));
                    s1.add(new WaitCommand(0.5));
                    s1.add(new ResetEncoders(container.drive));
                    s1.add(new BuiltDeadline(new AutoTankDrive(container.drive, -0.4, -0.4, 35), d -> {
                        d.add(new AutoIntake(container.intake, container.accumulator));
                    }));
                }));
            }));
        });
    }
}
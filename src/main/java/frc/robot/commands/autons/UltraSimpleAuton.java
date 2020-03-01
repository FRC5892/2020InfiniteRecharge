package frc.robot.commands.autons;

import frc.built_groups.BuiltParallel;
import frc.built_groups.BuiltSequence;
import frc.robot.RobotContainer;
import frc.robot.commands.AimAndShoot;
import frc.robot.subsystems.drive.AutoTankDrive;
import frc.robot.subsystems.shooter.ResetHood;

public class UltraSimpleAuton extends BuiltSequence {
    public UltraSimpleAuton(RobotContainer container, double power) {
        super(s -> {
            s.add(new AimAndShoot(container, false).withTimeout(10));
            s.add(new BuiltParallel(p -> {
                p.add(new AutoTankDrive(container.drive, power, power, 10).withTimeout(2));
                p.add(new ResetHood(container.shooter));
            }));
        });
    }
}
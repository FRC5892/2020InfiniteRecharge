package frc.robot.commands.autons;

import frc.built_groups.BuiltParallel;
import frc.built_groups.BuiltSequence;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.AimAndShoot;
import frc.robot.subsystems.drive.AutoTankDrive;
import frc.robot.subsystems.shooter.SuperHoodReset;

public class UltraSimpleAuton extends BuiltSequence {
    public UltraSimpleAuton(RobotContainer container, double power) {
        super(s -> {
            s.add(new AimAndShoot(container, 4000, Constants.HOOD_SETPOINT_INITIATION_LINE, 30).withTimeout(10));
            s.add(new BuiltParallel(p -> {
                p.add(new AutoTankDrive(container.drive, power, power, 10).withTimeout(2));
                p.add(new SuperHoodReset(container.shooter));
            }));
        });
    }
}
package frc.robot.commands;

import frc.built_groups.BuiltSequence;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drive.AutoTankDrive;
import frc.robot.subsystems.drive.ResetEncoders;

public class BackUpAndAimAndShoot extends BuiltSequence {
    public BackUpAndAimAndShoot(RobotContainer container) {
        super(s -> {
            s.add(new ResetEncoders(container.drive));
            s.add(new AutoTankDrive(container.drive, 0.1, 0.1, 4).withTimeout(0.5));
            s.add(new AimAndShoot(container, false));
        });
    }
}
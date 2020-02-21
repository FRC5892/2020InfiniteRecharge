package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.HoldSubsystems;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drive.VisionAlignCommand;
import frc.robot.subsystems.shooter.RunShooter;

public class AimAndShoot {
    private AimAndShoot() {
    }

    public static Command make(RobotContainer container) {
        var drive = container.drive;
        var intake = container.intake;
        var accumulator = container.accumulator;
        var shooter = container.shooter;

        return CommandGroupBase.deadline(CommandGroupBase.race(new RunShooter(accumulator, shooter, 130000, 1000),
                new VisionAlignCommand(drive)), new HoldSubsystems(intake));
    }
}
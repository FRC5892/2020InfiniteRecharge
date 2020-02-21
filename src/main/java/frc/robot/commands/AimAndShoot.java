package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.HoldSubsystems;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drive.VisionAlignCommand;
import frc.robot.subsystems.shooter.RunShooter;

public class AimAndShoot extends ParallelDeadlineGroup {
    public AimAndShoot(RobotContainer container) {
        super(CommandGroupBase.race(new RunShooter(container.accumulator, container.shooter, 130000, 1000),
                new VisionAlignCommand(container.drive)));
        addCommands(new HoldSubsystems(container.intake));
    }
}
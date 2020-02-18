package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.FilePIDController;

public class VisionAlignCommand extends PIDCommand {
    public VisionAlignCommand(DriveSubsystem drive) {
        super(new FilePIDController("/home/lvuser/deploy/PID/VisionAlign.txt"), () -> {
            return drive.container.limelight.xOffset();
        }, 0, output -> {
            drive.arcadeDrive(0, output);
        }, drive);
    }
}
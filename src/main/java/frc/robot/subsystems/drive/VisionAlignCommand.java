package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.FilePIDController;
import frc.robot.subsystems.limelight.Limelight;
import frc.robot.subsystems.limelight.Limelight.LEDMode;

public class VisionAlignCommand extends PIDCommand {
    private final Limelight limelight;

    public VisionAlignCommand(DriveSubsystem drive, Limelight limelight) {
        super(new FilePIDController("/home/lvuser/deploy/PID/VisionAlign.txt"), () -> {
            return limelight.xOffset();
        }, 0, output -> {
            drive.arcadeDrive(0, output);
        }, drive, limelight);
        this.limelight = limelight;
    }

    @Override
    public void initialize() {
        super.initialize();
        limelight.setLedMode(LEDMode.DEFAULT);
        limelight.setDriverCamera(false);
    }
}
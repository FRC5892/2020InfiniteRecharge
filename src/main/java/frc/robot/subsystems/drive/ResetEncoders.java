package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ResetEncoders extends CommandBase {
    private final DriveSubsystem drive;

    public ResetEncoders(DriveSubsystem drive) {
        this.drive = drive;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.resetEncoders();
    }

    @Override
    public boolean isFinished() {
        return drive.getLeftEncoder() == 0 && drive.getRightEncoder() == 0;
    }
}
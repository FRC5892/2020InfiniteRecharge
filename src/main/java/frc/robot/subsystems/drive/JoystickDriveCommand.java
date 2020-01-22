package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class JoystickDriveCommand extends CommandBase {
    private final DriveSubsystem drive;

    public JoystickDriveCommand(DriveSubsystem drive) {
        addRequirements(drive);
        this.drive = drive;
    }

    @Override
    public void execute() {
        drive.arcadeDrive(-drive.container.pilot.getRawAxis(1), drive.container.pilot.getRawAxis(4));
    }
}
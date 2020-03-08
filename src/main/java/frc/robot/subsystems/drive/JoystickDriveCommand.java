package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class JoystickDriveCommand extends CommandBase {
    private final DriveSubsystem drive;

    private boolean slowMode, toggleLastFrame;

    private static final double SLOW_SPEED = 0.5;
    private static final double SLOW_TURN = 0.5;
    private static final double FAST_SPEED = 0.9;
    private static final double FAST_TURN = 0.7;

    public JoystickDriveCommand(DriveSubsystem drive) {
        addRequirements(drive);
        this.drive = drive;
    }

    @Override
    public void execute() {
        if (!toggleLastFrame && drive.container.pilot.getRawButton(2)) {
            slowMode = !slowMode;
            toggleLastFrame = true;
        } else if (!drive.container.pilot.getRawButton(2)) {
            toggleLastFrame = false;
        }
        var speed = slowMode ? SLOW_SPEED : FAST_SPEED;
        var turn = slowMode ? SLOW_TURN : FAST_TURN;
        drive.arcadeDrive(drive.container.pilot.getRawAxis(1), -drive.container.pilot.getRawAxis(4), speed, turn);
    }
}
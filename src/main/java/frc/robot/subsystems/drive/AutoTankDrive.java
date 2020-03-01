package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoTankDrive extends CommandBase {
    private final DriveSubsystem drive;
    private final double left;
    private final double right;
    private final double encoderTarget;

    public AutoTankDrive(DriveSubsystem drive, double left, double right, double encoderTarget) {
        this.drive = drive;
        this.left = left;
        this.right = right;
        this.encoderTarget = encoderTarget;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        drive.resetEncoders();
    }

    @Override
    public void execute() {
        drive.tankDrive(left, right);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(drive.getLeftEncoder()) > encoderTarget || Math.abs(drive.getRightEncoder()) > encoderTarget;
    }
    @Override
    public void end(boolean interrupted) {
        drive.tankDrive(0, 0);
    }

}
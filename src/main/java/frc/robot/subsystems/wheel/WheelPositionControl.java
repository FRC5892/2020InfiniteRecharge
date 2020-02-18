package frc.robot.subsystems.wheel;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class WheelPositionControl extends CommandBase {
    private static final double WINDING_DOWN_TIME = 0.5; // seconds

    private final WheelSubsystem wheel;
    private final double speed;

    private boolean clockwiseLast;
    private boolean windingDownLast;
    private double windingDownTimestamp;

    public WheelPositionControl(WheelSubsystem wheel, double speed) {
        this.wheel = wheel;
        this.speed = speed;
        addRequirements(wheel);
    }

    @Override
    public void initialize() {
        clockwiseLast = true;
        windingDownLast = false;
        windingDownTimestamp = 0;
    }

    @Override
    public void execute() {
        switch (wheel.colors.directionToTurn()) {
        case CLOCKWISE:
            clockwiseLast = true;
            windingDownLast = false;
            wheel.setManipulator(speed);
            break;
        case COUNTERCLOCKWISE:
            clockwiseLast = false;
            windingDownLast = false;
            wheel.setManipulator(-speed);
            break;
        case CONTINUE:
            windingDownLast = false;
            wheel.setManipulator(clockwiseLast ? speed : -speed);
            break;
        case STOP:
            if (!windingDownLast) {
                windingDownLast = true;
                clockwiseLast = !clockwiseLast;
                windingDownTimestamp = Timer.getFPGATimestamp();
            }
            wheel.setManipulator(0);
            break;
        }
    }

    @Override
    public boolean isFinished() {
        return wheel.colors.colorTheySee() == wheel.colors.colorTheyWant()
                && Timer.getFPGATimestamp() + WINDING_DOWN_TIME > windingDownTimestamp;
    }

    @Override
    public void end(boolean interrupted) {
        wheel.setManipulator(0);
    }
}
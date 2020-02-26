package frc.robot.subsystems.wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class WheelRotationControl extends CommandBase {
    // 4 rotations * ~2 ticks/rotation * 32" wheel of fortune / 3" manipulator
    private static final int TARGET = 4 * 2 * 32 / 3;

    private final WheelSubsystem wheel;
    private final double speed;

    public WheelRotationControl(WheelSubsystem wheel, double speed) {
        this.wheel = wheel;
        this.speed = speed;
        addRequirements(wheel);
    }

    @Override
    public void initialize() {
        wheel.resetCounter();
    }

    @Override
    public void execute() {
        wheel.setManipulator(speed);
    }

    @Override
    public boolean isFinished() {
        return wheel.getCounter() >= TARGET;
    }

    @Override
    public void end(boolean interrupted) {
        wheel.setManipulator(0);
    }
}
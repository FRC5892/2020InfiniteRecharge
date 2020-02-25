package frc.robot.subsystems.wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SetWheelPiston extends CommandBase {
    private final WheelSubsystem wheel;
    private final boolean on;

    public SetWheelPiston(WheelSubsystem wheel, boolean on) {
        this.wheel = wheel;
        this.on = on;
        addRequirements(wheel);
    }

    @Override
    public void execute() {
        if (on) {
            wheel.extendPiston();
        } else {
            wheel.retractPiston();
        } 
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
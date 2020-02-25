package frc.robot.subsystems.wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;

@Deprecated
public class JoystickWheelCommand extends CommandBase {
    private final WheelSubsystem wheel;

    public JoystickWheelCommand(WheelSubsystem wheel) {
        addRequirements(wheel);
        this.wheel = wheel;
    }

    @Override
    public void execute() {
        wheel.setManipulator(wheel.container.copilot.getRawButton(2) ? 0.45 : 0);
        var pov = wheel.container.copilot.getPOV();
        if (pov == 0) {
            wheel.extendPiston();
        } else if (pov == 180) {
            wheel.retractPiston();
        }
    }
}
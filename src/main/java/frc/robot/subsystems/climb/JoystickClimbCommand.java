package frc.robot.subsystems.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class JoystickClimbCommand extends CommandBase {
    private final ClimbSubsystem climb;

    public JoystickClimbCommand(ClimbSubsystem climb) {
        this.climb = climb;
        addRequirements(climb);
    }

    @Override
    public void execute() {
        climb.setArm(climb.container.copilot.getRawAxis(5));
        climb.setWinch(climb.container.copilot.getRawButton(10) ? 1 : 0);
        if (climb.container.copilot.getRawButton(8)) {
            climb.extendArmPiston();
        } else if (climb.container.copilot.getRawButton(7)) {
            climb.retractArmPiston();
        }
    }
}
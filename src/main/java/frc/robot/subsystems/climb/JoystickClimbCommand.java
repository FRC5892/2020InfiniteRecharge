package frc.robot.subsystems.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.MathUtils;

public class JoystickClimbCommand extends CommandBase {
    private final ClimbSubsystem climb;

    public JoystickClimbCommand(ClimbSubsystem climb) {
        this.climb = climb;
        addRequirements(climb);
    }

    @Override
    public void execute() {
        climb.setArm(MathUtils.signedSquare(MathUtils.deadZone(climb.container.copilot.getRawAxis(5), 0.1)));
        climb.setWinch(
                MathUtils.signedSquare(climb.container.copilot.getRawAxis(3) - climb.container.copilot.getRawAxis(2)));
        if (climb.container.copilot.getRawButton(8)) {
            climb.extendArmPiston();
        } else if (climb.container.copilot.getRawButton(7)) {
            climb.retractArmPiston();
        }
    }
}
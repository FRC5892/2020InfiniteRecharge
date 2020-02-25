package frc.robot.subsystems.climb;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class JoystickClimbCommand extends CommandBase {
    private final ClimbSubsystem climb;

    public JoystickClimbCommand(ClimbSubsystem climb) {
        this.climb = climb;
        addRequirements(climb);
    }

    @Override
    public void execute() {
        if (DriverStation.getInstance().isFMSAttached() && DriverStation.getInstance().getMatchTime() > 30) {
            return;
        }

        climb.setArm(climb.container.copilot.getRawAxis(5));
        climb.setWinch(climb.container.copilot.getRawAxis(3)-climb.container.copilot.getRawAxis(2));
        if (climb.container.copilot.getRawButton(8)) {
            climb.extendArmPiston();
        } else if (climb.container.copilot.getRawButton(7)) {
            climb.retractArmPiston();
        }
    }
}
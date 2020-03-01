package frc.robot.subsystems.climb;

import edu.wpi.first.wpilibj.DriverStation;
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
        if (DriverStation.getInstance().isFMSAttached() && DriverStation.getInstance().getMatchTime() > 30) {
            climb.setArm(0);
            climb.setWinch(0);
            climb.retractArmPiston();
        } else {
            climb.setArm(MathUtils.deadZone(climb.container.copilot.getRawAxis(5), 0.1));
            climb.setWinch(climb.container.copilot.getRawAxis(3));
            if (climb.container.copilot.getRawButton(8)) {
                climb.extendArmPiston();
            } else if (climb.container.copilot.getRawButton(7)) {
                climb.retractArmPiston();
            }
        }
    }
}
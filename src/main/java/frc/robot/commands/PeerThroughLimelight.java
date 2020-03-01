package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class PeerThroughLimelight extends CommandBase {
    private final RobotContainer container;

    public PeerThroughLimelight(RobotContainer container) {
        this.container = container;
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    @Override
    public void initialize() {
        //container.limelight.setLedMode(LEDMode.OFF);
        container.limelight.setDriverCamera(true);
    }

    @Override
    public void end(boolean interrupted) {
        //container.limelight.setLedMode(LEDMode.DEFAULT);
        container.limelight.setDriverCamera(false);
    }
}
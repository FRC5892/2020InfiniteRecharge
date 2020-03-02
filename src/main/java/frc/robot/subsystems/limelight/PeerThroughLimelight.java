package frc.robot.subsystems.limelight;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class PeerThroughLimelight extends CommandBase {
    private final Limelight limelight;

    public PeerThroughLimelight(Limelight limelight) {
        this.limelight = limelight;
        addRequirements(limelight);
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    @Override
    public void initialize() {
        //container.limelight.setLedMode(LEDMode.OFF);
        limelight.setDriverCamera(true);
    }

    @Override
    public void end(boolean interrupted) {
        //container.limelight.setLedMode(LEDMode.DEFAULT);
        limelight.setDriverCamera(false);
    }
}
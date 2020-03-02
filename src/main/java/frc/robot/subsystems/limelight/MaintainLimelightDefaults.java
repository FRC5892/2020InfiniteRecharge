package frc.robot.subsystems.limelight;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.limelight.Limelight.LEDMode;

public class MaintainLimelightDefaults extends CommandBase {
    private final Limelight limelight;

    public MaintainLimelightDefaults(Limelight limelight) {
        this.limelight = limelight;
        addRequirements(limelight);
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    @Override
    public void execute() {
        limelight.setDriverCamera(false);
        if (DriverStation.getInstance().isEnabled()) {
            limelight.setLedMode(LEDMode.DEFAULT);
        } else {
            limelight.setLedMode(LEDMode.OFF);
        }
    }
}
package frc;

import edu.wpi.first.wpilibj.SpeedController;

public class DummySpeedController implements SpeedController {
    public DummySpeedController() {}
    public DummySpeedController(int port) {}

    @Override
    public void pidWrite(double output) {
    }

    @Override
    public void set(double speed) {
    }

    @Override
    public double get() {
        return 0;
    }

    @Override
    public void setInverted(boolean isInverted) {
    }

    @Override
    public boolean getInverted() {
        return false;
    }

    @Override
    public void disable() {
    }

    @Override
    public void stopMotor() {
    }
}
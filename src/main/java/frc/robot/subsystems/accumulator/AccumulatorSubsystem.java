package frc.robot.subsystems.accumulator;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.MotorSpecs;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class AccumulatorSubsystem extends SubsystemBase {
    public final RobotContainer container;
    private final SpeedController belt;
    private final SpeedController kicker;
    private final DigitalInput ballSensor;

    public AccumulatorSubsystem(RobotMap map, RobotContainer container) {
        this.container = container;
        belt = MotorSpecs.makeSpeedControllers(map.accumulatorBelt, "Belt", this);
        kicker = MotorSpecs.makeSpeedControllers(map.accumulatorKicker, "Kicker", this);
        ballSensor = new DigitalInput(map.accumulatorBallSensor);
        addChild("Ball Sensor", ballSensor);
    }

    public void setBelt(double speed) {
        belt.set(speed);
    }

    public void setKicker(double speed) {
        kicker.set(speed);
    }

    public void set(double speed) {
        setBelt(speed);
        setKicker(speed);
    }

    public boolean seesBall() {
        return ballSensor.get();
    }
}
package frc.robot.subsystems.shooter;

import java.io.IOException;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.MotorSpecs;
import frc.SparkMaxUtils;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class ShooterSubsystem extends SubsystemBase {
    private static final boolean TUNING_MODE = true;
    private static final double FLYWHEEL_THRESHOLD = 25; // SPARK MAX speed units (rpm?)

    public final RobotContainer container;
    private final CANSparkMax flywheel;
    private final SpeedController hood;
    private final Counter hoodCounter;
    private final DigitalInput limitSwitch;

    private double flywheelSetpoint = Double.NaN;

    public ShooterSubsystem(RobotMap map, RobotContainer container) {
        this.container = container;
        flywheel = MotorSpecs.makeSparkMaxes(map.shooterFlywheel);
        try {
            SparkMaxUtils.readPID(flywheel, "Flywheel", TUNING_MODE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        hood = MotorSpecs.makeSpeedControllers(map.shooterHood, "Hood", this);
        hoodCounter = new Counter(map.shooterHoodCounter);
        addChild("Hood Counter", hoodCounter);
        hoodCounter.setUpSourceEdge(false, true);
        limitSwitch = new DigitalInput(map.shooterLimitSwitch);
        addChild("Limit Switch", limitSwitch);
        setDefaultCommand(new ManualShooterControl(this));
    }

    public void setFlywheelSpeed(double speed) {
        flywheel.set(speed);
        flywheelSetpoint = Double.NaN;
    }

    public void setFlywheelSetpoint(double setpoint) {
        flywheel.getPIDController().setReference(setpoint, ControlType.kVelocity);
        flywheelSetpoint = setpoint;
    }

    public void stopFlywheel() {
        flywheel.stopMotor();
        flywheelSetpoint = Double.NaN;
    }

    public void setHoodSpeed(double speed) {
        hood.set(speed);
    }

    public void stopHood() {
        hood.stopMotor();
    }

    public int getHoodCounter() {
        return hoodCounter.get();
    }

    public boolean hoodAtBaseline() {
        return !limitSwitch.get();
    }

    public boolean flywheelAtSetpoint() {
        return Math.abs(flywheel.getEncoder().getVelocity() - flywheelSetpoint) < FLYWHEEL_THRESHOLD;
    }

    @Override
    public void periodic() {
        if (hoodAtBaseline()) {
            hoodCounter.reset();
        }
    }
}

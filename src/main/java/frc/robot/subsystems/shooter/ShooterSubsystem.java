package frc.robot.subsystems.shooter;

import java.io.IOException;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.FilePIDController;
import frc.MotorSpecs;
import frc.SparkMaxUtils;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class ShooterSubsystem extends SubsystemBase {
    private static final boolean TUNING_MODE = false;
    private static final double HOOD_THRESHOLD = 2;

    public final RobotContainer container;
    private final CANSparkMax flywheel;
    private final SpeedController hood;
    private final Counter hoodCounter;
    private final DigitalInput lowerLimit, upperLimit;
    private final PIDController hoodController;

    //private double flywheelSetpoint = Double.NaN;
    private boolean hoodControllerEnabled;

    public ShooterSubsystem(RobotMap map, RobotContainer container) {
        this.container = container;
        flywheel = MotorSpecs.makeSparkMaxAndFollowers(map.shooterFlywheel);
        try {
            SparkMaxUtils.readPID(flywheel, "Flywheel", TUNING_MODE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        hood = MotorSpecs.makeSpeedControllers(map.shooterHood, "Hood", this);

        hoodCounter = new Counter(map.shooterHoodCounter);
        addChild("Hood Counter", hoodCounter);
        hoodCounter.setUpSourceEdge(false, true);

        lowerLimit = new DigitalInput(map.shooterLowerLimit);
        addChild("Lower Limit", lowerLimit);

        upperLimit = new DigitalInput(map.shooterUpperLimit);
        addChild("Upper Limit", upperLimit);

        hoodController = new FilePIDController("/home/lvuser/deploy/PID/Hood.txt");
        addChild("Hood Controller", hoodController);
        hoodController.setTolerance(HOOD_THRESHOLD);

        setDefaultCommand(new ResetHood(this, false));
    }

    public void setFlywheelSpeed(double speed) {
        flywheel.set(speed);
        //flywheelSetpoint = Double.NaN;
    }

    public void setFlywheelSetpoint(double setpoint) {
        flywheel.getPIDController().setReference(setpoint, ControlType.kVelocity);
        //flywheelSetpoint = setpoint;
    }

    public void stopFlywheel() {
        flywheel.stopMotor();
        //flywheelSetpoint = Double.NaN;
    }

    public void setHoodSpeed(double speed) {
        hood.set(speed);
        hoodController.reset();
        hoodControllerEnabled = false;
    }

    public void setHoodSetpoint(double setpoint) {
        hoodController.setSetpoint(setpoint);
        hoodControllerEnabled = true;
    }

    public void stopHood() {
        hood.stopMotor();
        hoodController.reset();
        hoodControllerEnabled = false;
    }

    public int getHoodCounter() {
        return hoodCounter.get();
    }

    public boolean hoodAtBaseline() {
        return !lowerLimit.get();
    }

    public boolean hoodAtExtent() {
        return upperLimit.get();
    }

    public double getFlywheelSpeed() {
        return flywheel.getEncoder().getVelocity();
    }

    public boolean hoodAtSetpoint() {
        return hoodControllerEnabled
                && (getHoodCounter() > (hoodController.getSetpoint() - HOOD_THRESHOLD) || hoodAtExtent());
    }

    @Override
    public void periodic() {
        if (hoodAtBaseline()) {
            hoodCounter.reset();
        }
        if (hoodControllerEnabled) {
            var hoodSpeed = hoodController.calculate(getHoodCounter());
            if (hoodAtExtent() || hoodSpeed < 0) {
                hood.set(0);
            } else {
                hood.set(hoodSpeed);
            }
        }
    }
}

package frc.robot.subsystems.shooter;

import java.io.IOException;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.FilePIDController;
import frc.MotorSpecs;
import frc.SparkMaxUtils;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class ShooterSubsystem extends SubsystemBase {
    private static final boolean TUNING_MODE = true;
    private static final double FLYWHEEL_THRESHOLD = 10_000; // SPARK MAX speed units
    private static final double HOOD_THRESHOLD = 20; // encoder ticks
    private static final double HOOD_VELOCITY_THRESHOLD = 5; // encoder ticks per some unit of time idk

    public final RobotContainer container;
    private final CANSparkMax flywheel;
    private final SpeedController hood;
    private final Encoder hoodEncoder;
    private final DigitalInput limitSwitch;
    private final PIDController hoodController;

    private double flywheelSetpoint = Double.NaN;
    private boolean hoodControllerEnabled;

    public ShooterSubsystem(RobotMap map, RobotContainer container) {
        this.container = container;
        flywheel = MotorSpecs.makeSparkMaxes(map.shooterFlywheel);
        try {
            SparkMaxUtils.readPID(flywheel, "Flywheel", TUNING_MODE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        hood = MotorSpecs.makeSpeedControllers(map.shooterHood, "Hood", this);
        hoodEncoder = new Encoder(map.shooterHoodEncoder[0], map.shooterHoodEncoder[1]);
        addChild("Hood Encoder", hoodEncoder);
        limitSwitch = new DigitalInput(map.shooterLimitSwitch);
        addChild("Limit Switch", limitSwitch);
        hoodController = new FilePIDController("/home/lvuser/deploy/PID/Hood.txt");
        addChild("Hood Controller", hoodController);
        hoodController.setTolerance(HOOD_THRESHOLD, HOOD_VELOCITY_THRESHOLD);
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

    public boolean hoodAtBaseline() {
        return limitSwitch.get();
    }

    public boolean atSetpoints() {
        if (Double.isNaN(flywheelSetpoint) || !hoodControllerEnabled) {
            return false;
        }
        return Math.abs(flywheel.getEncoder().getVelocity() - flywheelSetpoint) < FLYWHEEL_THRESHOLD
                && hoodController.atSetpoint();
    }

    @Override
    public void periodic() {
        if (hoodAtBaseline()) {
            hoodEncoder.reset();
        }
        if (hoodControllerEnabled) {
            hood.set(hoodController.calculate(hoodEncoder.get()));
        }
    }
}

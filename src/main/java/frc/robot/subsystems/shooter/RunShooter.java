package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.accumulator.AccumulatorSubsystem;

public class RunShooter extends CommandBase {
    private static final double MANUAL_HOOD_SPEED = 0.1;


    private final AccumulatorSubsystem accumulator;
    private final ShooterSubsystem shooter;

    private final double flywheelSetpoint, hoodSetpoint, flywheelThreshold;
    private boolean hoodOverride;

    public RunShooter(AccumulatorSubsystem accumulator, ShooterSubsystem shooter, double flywheelSetpoint,
            double hoodSetpoint, double flywheelThreshold) {
        this.accumulator = accumulator;
        this.shooter = shooter;
        this.flywheelSetpoint = flywheelSetpoint;
        this.hoodSetpoint = hoodSetpoint;
        this.flywheelThreshold = flywheelThreshold;
        addRequirements(accumulator, shooter);
    }

    @Override
    public void initialize() {
        shooter.setFlywheelSetpoint(flywheelSetpoint);
        shooter.setHoodSetpoint(hoodSetpoint);
        hoodOverride = false;
    }

    @Override
    public void execute() {
        if (shooter.hoodAtSetpoint() && Math.abs(flywheelSetpoint - shooter.getFlywheelSpeed()) < flywheelThreshold) {
            accumulator.setBelt(0.75);
            accumulator.setKicker(0.85);
        } else if (!accumulator.seesBall()) {
            accumulator.set(0.5);
        } else {
            accumulator.set(0);
        }

        if (shooter.container.copilot.getRawButton(1)) {
            shooter.setHoodSpeed(-MANUAL_HOOD_SPEED);
            hoodOverride = true;
        } else if (shooter.container.copilot.getRawButton(4)) {
            shooter.setHoodSpeed(MANUAL_HOOD_SPEED);
            hoodOverride = true;
        } else if (hoodOverride) {
            shooter.stopHood();
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopFlywheel();
        shooter.stopHood();
        accumulator.set(0);
    }
}
package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.accumulator.AccumulatorSubsystem;

public class RunShooter extends CommandBase {
    private final AccumulatorSubsystem accumulator;
    private final ShooterSubsystem shooter;

    private final double flywheelSetpoint, hoodSetpoint;

    public RunShooter(AccumulatorSubsystem accumulator, ShooterSubsystem shooter, double flywheelSetpoint,
            double hoodSetpoint) {
        this.accumulator = accumulator;
        this.shooter = shooter;
        this.flywheelSetpoint = flywheelSetpoint;
        this.hoodSetpoint = hoodSetpoint;
        addRequirements(accumulator, shooter);
    }

    @Override
    public void initialize() {
        shooter.setFlywheelSetpoint(flywheelSetpoint);
        shooter.setHoodSetpoint(hoodSetpoint);
    }

    @Override
    public void execute() {
        if (shooter.atSetpoints()) {
            accumulator.setBelt(0.75);
            accumulator.setKicker(0.85);
        } else if (!accumulator.seesBall()) {
            accumulator.set(0.5);
        } else {
            accumulator.set(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopFlywheel();
        shooter.stopHood();
        accumulator.set(0);
    }
}
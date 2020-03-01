package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.accumulator.AccumulatorSubsystem;

public class RunShooter extends CommandBase {
    private final AccumulatorSubsystem accumulator;
    private final ShooterSubsystem shooter;

    private final double flywheelSetpoint;
    private final boolean moveHood;

    public RunShooter(AccumulatorSubsystem accumulator, ShooterSubsystem shooter, double flywheelSetpoint,
            boolean moveHood) {
        this.accumulator = accumulator;
        this.shooter = shooter;
        this.flywheelSetpoint = flywheelSetpoint;
        this.moveHood = moveHood;
        addRequirements(accumulator, shooter);
    }

    @Override
    public void initialize() {
        shooter.setFlywheelSetpoint(flywheelSetpoint);
    }

    @Override
    public void execute() {
        if (!shooter.hoodAtExtent() && moveHood && shooter.container.hoodZeroing()) {
            shooter.setHoodSpeed(0.5);
            accumulator.set(accumulator.seesBall() ? 0 : 0.5);
        } else {
            shooter.stopHood();
            if (shooter.flywheelAtSetpoint()) {
                accumulator.setBelt(0.5);
                accumulator.setKicker(0.85);
            } else {
                accumulator.set(accumulator.seesBall() ? 0 : 0.5);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopFlywheel();
        shooter.stopHood();
        accumulator.set(0);
    }
}
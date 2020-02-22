package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ResetHood extends CommandBase {
    private final ShooterSubsystem shooter;

    public ResetHood(ShooterSubsystem shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.setHoodSpeed(-0.75);
    }

    @Override
    public boolean isFinished() {
        return shooter.hoodAtBaseline();
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopHood();
    }
}
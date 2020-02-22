package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ResetHood extends CommandBase {
    private final ShooterSubsystem shooter;

    private final double speed;

    public ResetHood(ShooterSubsystem shooter) {
        this(shooter, 0.75);
    }

    public ResetHood(ShooterSubsystem shooter, double speed) {
        this.shooter = shooter;
        this.speed = Math.copySign(speed, -1);
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.setHoodSpeed(speed);
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
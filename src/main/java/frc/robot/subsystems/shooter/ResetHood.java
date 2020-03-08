package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ResetHood extends CommandBase {
    private final ShooterSubsystem shooter;

    private final boolean end;
    private final double speed;

    public ResetHood(ShooterSubsystem shooter) {
        this(shooter, true);
    }

    public ResetHood(ShooterSubsystem shooter, boolean end) {
        this(shooter, end, 0.75);
    }

    public ResetHood(ShooterSubsystem shooter, boolean end, double speed) {
        this.shooter = shooter;
        this.end = end;
        this.speed = Math.copySign(speed, -1);
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        if (!shooter.hoodAtBaseline() && shooter.container.hoodZeroing()) {
            shooter.setHoodSpeed(speed);
        } else {
            shooter.stopHood();
        }
    }

    @Override
    public boolean isFinished() {
        return end && (shooter.hoodAtBaseline() || !shooter.container.hoodZeroing());
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopHood();
    }
}
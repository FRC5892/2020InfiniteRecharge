package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ManualShooterControl extends CommandBase {
    private final ShooterSubsystem shooter;

    public ManualShooterControl(ShooterSubsystem shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        //shooter.setFlywheelSpeed(0.85 * shooter.container.copilot.getRawAxis(3));
        if (shooter.container.copilot.getRawButton(4) && !shooter.hoodAtExtent()) {
            shooter.setHoodSpeed(0.75);
        } else if (shooter.container.copilot.getRawButton(3)) {
            shooter.setHoodSpeed(-0.75);
        } else {
            shooter.stopHood();
        }
    }
}
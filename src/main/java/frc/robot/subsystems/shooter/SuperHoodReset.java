package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.built_groups.BuiltSequence;

public class SuperHoodReset extends BuiltSequence {
    public SuperHoodReset(ShooterSubsystem shooter) {
        super(s -> {
            s.add(new ResetHood(shooter, true, 0.75));
            s.add(new RunCommand(() -> shooter.setHoodSpeed(1), shooter).withTimeout(0.25));
            s.add(new ResetHood(shooter, true, 0.25));
        });
    }
}
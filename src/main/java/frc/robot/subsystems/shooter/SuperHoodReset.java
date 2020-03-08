package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.built_groups.BuiltSequence;

public class SuperHoodReset extends BuiltSequence {
    public SuperHoodReset(ShooterSubsystem shooter) {
        super(s -> {
            s.add(new ResetHood(shooter, true, 0.75));
            s.add(new RunCommand(() -> shooter.setHoodSpeed(shooter.container.hoodZeroing() ? 1 : 0), shooter)
                    .withInterrupt(() -> !shooter.container.hoodZeroing()).withTimeout(0.25));
            s.add(new ResetHood(shooter, true, 0.25));
        });
    }
}
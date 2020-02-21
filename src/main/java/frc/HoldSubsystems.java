package frc;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * A {@link Command} that requires the subsystems passed to it, and does nothing
 * else.
 * 
 * It never finishes on its own; be sure to cancel it once you want the
 * subsystems back.
 */
public class HoldSubsystems extends CommandBase {
    public HoldSubsystems(Subsystem... subsystems) {
        addRequirements(subsystems);
    }
}
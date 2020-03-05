package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import frc.robot.subsystems.accumulator.AccumulatorSubsystem;
import frc.robot.subsystems.limelight.Limelight;

public class RunShooterContextual extends ConditionalCommand {

    public RunShooterContextual(AccumulatorSubsystem accumulator, ShooterSubsystem shooter, Limelight limelight) {
        super(new RunShooter(accumulator, shooter, 4000, 50), new RunShooter(accumulator, shooter, 4000, 45),
                () -> limelight.estimateTargetDistance() > 200);
    }
}
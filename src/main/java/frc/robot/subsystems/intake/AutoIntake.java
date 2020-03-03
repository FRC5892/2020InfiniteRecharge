package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.accumulator.AccumulatorSubsystem;

public class AutoIntake extends CommandBase {
    private final IntakeSubsystem intake;
    private final AccumulatorSubsystem accumulator;
    
    public AutoIntake(IntakeSubsystem intake, AccumulatorSubsystem accumulator) {
        this.intake = intake;
        this.accumulator = accumulator;
        addRequirements(intake, accumulator);
    }

    @Override
    public void execute() {
        intake.extendPistons();
        intake.setRollers(0.8);
        if (!accumulator.seesBall()) {
            accumulator.set(0.85);
        } else {
            accumulator.set(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.setRollers(0);
    }
}
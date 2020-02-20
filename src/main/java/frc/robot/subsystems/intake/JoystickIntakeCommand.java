package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.accumulator.AccumulatorSubsystem;

public class JoystickIntakeCommand extends CommandBase {
    private static final double ACC_SCALE = 0.5;

    private final IntakeSubsystem intake;
    private final AccumulatorSubsystem accumulator;

    public JoystickIntakeCommand(IntakeSubsystem intake, AccumulatorSubsystem accumulator) {
        this.intake = intake;
        this.accumulator = accumulator;
        addRequirements(intake, accumulator);
    }

    @Override
    public void execute() {
        var rollerSpeed = intake.container.pilot.getRawAxis(3) - intake.container.pilot.getRawAxis(2);
        intake.setRollers(rollerSpeed);
        if (rollerSpeed > 0 && !accumulator.seesBall()) {
            accumulator.set(rollerSpeed * ACC_SCALE);
        }
        if (rollerSpeed > 0.1) {
            intake.extendPistons();
        } else if (intake.container.pilot.getRawButton(6)) {
            intake.retractPistons();
        }
    }
}
package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SoloJoystickIntake extends CommandBase {
    private final IntakeSubsystem intake;

    public SoloJoystickIntake(IntakeSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        var rollerSpeed = intake.container.pilot.getRawAxis(3) - intake.container.pilot.getRawAxis(2);
        intake.setRollers(rollerSpeed);
        if (rollerSpeed > 0.1) {
            intake.extendPistons();
        } else if (intake.container.pilot.getRawButton(6)) {
            intake.retractPistons();
        }
    }
}
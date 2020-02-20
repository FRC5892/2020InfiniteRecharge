package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.MotorSpecs;
import frc.SolenoidGroup;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class IntakeSubsystem extends SubsystemBase {
    public final RobotContainer container;
    private final SpeedController rollers;
    private final SolenoidGroup pistons;

    public IntakeSubsystem(RobotMap map, RobotContainer container) {
        this.container = container;
        rollers = MotorSpecs.makeSpeedControllers(map.intakeRollers, "Rollers", this);
        pistons = SolenoidGroup.forPorts(map.intakePistons);
        addChild("Pistons", pistons);
    }

    public void setRollers(double speed) {
        rollers.set(speed);
    }

    public void extendPistons() {
        pistons.set(false);
    }

    public void retractPistons() {
        pistons.set(true);
    }
}
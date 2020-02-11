package frc.robot.subsystems.wheel;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.DoubleSolenoidGroup;
import frc.MotorSpecs;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class WheelSubsystem extends SubsystemBase {
    public final RobotContainer container;
    public final WheelColorHandler colors;
    private final SpeedController manipulator;
    private final DoubleSolenoidGroup piston;

    public WheelSubsystem(RobotMap map, RobotContainer container) {
        this.container = container;
        colors = new WheelColorHandler();
        manipulator = MotorSpecs.makeSpeedControllers(map.wheelManipulator);
        piston = new DoubleSolenoidGroup(map.wheelPiston);

        setDefaultCommand(new JoystickWheelCommand(this));
    }

    public void setManipulator(double speed) {
        manipulator.set(speed);
    }

    public void extendPiston() {
        piston.set(Value.kForward);
    }

    public void retractPiston() {
        piston.set(Value.kReverse);
    }
}
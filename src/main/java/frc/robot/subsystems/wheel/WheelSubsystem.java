package frc.robot.subsystems.wheel;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.SolenoidGroup;
import frc.MotorSpecs;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class WheelSubsystem extends SubsystemBase {
    public final RobotContainer container;
    public final WheelColorHandler colors;
    private final SpeedController manipulator;
    private final SolenoidGroup piston;
    private final Counter counter;

    public WheelSubsystem(RobotMap map, RobotContainer container) {
        this.container = container;
        colors = new WheelColorHandler();
        manipulator = MotorSpecs.makeSpeedControllers(map.wheelManipulator, "Manipulator", this);
        piston = SolenoidGroup.forPorts(map.wheelPiston);
        addChild("Piston", piston);
        counter = new Counter(map.wheelCounter);
        counter.setUpSourceEdge(false, true);
        addChild("Counter", counter);

        setDefaultCommand(new JoystickWheelCommand(this));
    }

    public void setManipulator(double speed) {
        manipulator.set(speed);
    }

    public void extendPiston() {
        piston.set(true);
    }

    public void retractPiston() {
        piston.set(false);
    }

    public int getCounter() {
        return counter.get();
    }

    public void resetCounter() {
        counter.reset();
    }
}
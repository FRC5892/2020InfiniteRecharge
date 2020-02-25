package frc.robot.subsystems.climb;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.MotorSpecs;
import frc.SolenoidGroup;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class ClimbSubsystem extends SubsystemBase {
    public final RobotContainer container;
    private final SpeedController arm;
    private final SpeedController winch;
    private final SolenoidGroup armPiston;
    private final SolenoidGroup winchLock;

    public ClimbSubsystem(RobotMap map, RobotContainer container) {
        this.container = container;
        arm = MotorSpecs.makeSpeedControllers(map.climbArm, "Arm", this);
        winch = MotorSpecs.makeSpeedControllers(map.climbWinch, "Winch", this);
        armPiston = SolenoidGroup.forPorts(map.climbPiston);
        addChild("Arm Piston", armPiston);
        winchLock = SolenoidGroup.forPorts(map.climbWinchLock);
        addChild("Winch Lock", winchLock);
        setDefaultCommand(new JoystickClimbCommand(this));
    }

    public void setArm(double speed) {
        arm.set(speed);
    }

    public void setWinch(double speed) {
        winch.set(speed);
        if (speed < 0) {
            winchLock.set(true);
        } else {
            winchLock.set(false);
        }
    }

    public void extendArmPiston() {
        armPiston.set(true);
    }

    public void retractArmPiston() {
        armPiston.set(false);
    }

}
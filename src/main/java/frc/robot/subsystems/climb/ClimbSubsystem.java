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

    public ClimbSubsystem(RobotMap map, RobotContainer container) {
        this.container = container;
        arm = MotorSpecs.makeSpeedControllers(map.climbArm);
        winch = MotorSpecs.makeSpeedControllers(map.climbWinch);
        armPiston = SolenoidGroup.forPorts(map.climbPiston);
        setDefaultCommand(new JoystickClimbCommand(this));

    }

    public void setArm(double speed) {
        arm.set(speed);
    }

    public void setWinch(double speed) {
        winch.set(speed);
    }

    public void extendArmPiston() {
        armPiston.set(true);
    }

    public void retractArmPiston() {
        armPiston.set(false);
    }

}
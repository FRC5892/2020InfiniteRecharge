package frc.robot;

import frc.MotorSpecs;

public class RobotMap {
    public MotorSpecs[] leftDrive;
    public MotorSpecs[] rightDrive;

    public MotorSpecs[] wheelManipulator;
    public int[][] wheelPiston;
    public int wheelCounter;

    public MotorSpecs[] intakeRollers;
    public int[][] intakePistons;

    public MotorSpecs[] climbArm;
    public MotorSpecs[] climbWinch;
    public int[][] climbPiston;

    public MotorSpecs[] shooterFlywheel;
    public MotorSpecs[] shooterKicker;
    public MotorSpecs[] shooterHood;
    public int[] shooterHoodEncoder;
    public int shooterLimitSwitch;
    public int shooterBallSensor;

}
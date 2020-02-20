package frc.robot;

import frc.MotorSpecs;

public class RobotMap {
    public MotorSpecs[] leftDrive;
    public MotorSpecs[] rightDrive;

    public MotorSpecs[] intakeRollers;
    public int[][] intakePistons;

    public MotorSpecs[] accumulatorBelt;
    public MotorSpecs[] accumulatorKicker;
    public int accumulatorBallSensor;

    public MotorSpecs[] shooterFlywheel;
    public MotorSpecs[] shooterHood;
    public int[] shooterHoodEncoder;
    public int shooterLimitSwitch;

    public MotorSpecs[] wheelManipulator;
    public int[][] wheelPiston;
    public int wheelCounter;

    public MotorSpecs[] climbArm;
    public MotorSpecs[] climbWinch;
    public int[][] climbPiston;
}
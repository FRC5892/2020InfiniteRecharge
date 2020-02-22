package frc.robot;

import frc.MotorSpecs;

public class RobotMap {
    // screw the fact that this needs to be here.
    public String $schema;

    public MotorSpecs[] leftDrive;
    public MotorSpecs[] rightDrive;

    public MotorSpecs[] intakeRollers;
    public int[][] intakePistons;

    public MotorSpecs[] accumulatorBelt;
    public MotorSpecs[] accumulatorKicker;
    public int accumulatorBallSensor;

    public MotorSpecs[] shooterFlywheel;
    public MotorSpecs[] shooterHood;
    public int shooterHoodCounter;
    public int shooterLimitSwitch;

    public MotorSpecs[] wheelManipulator;
    public int[][] wheelPiston;
    public int wheelCounter;

    public MotorSpecs[] climbArm;
    public MotorSpecs[] climbWinch;
    public int[][] climbPiston;

    public int pressureSensor;
}
package frc.robot.subsystems.drive;

import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.MotorSpecs;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class DriveSubsystem extends SubsystemBase {
    public final RobotContainer container;
    private final DifferentialDrive drive;
    private final CANEncoder leftEncoder, rightEncoder;

    public DriveSubsystem(RobotMap map, RobotContainer container) {
        this.container = container;
        var left = MotorSpecs.makeSparkMaxes(map.leftDrive);
        var right = MotorSpecs.makeSparkMaxes(map.rightDrive);
        drive = new DifferentialDrive(left, right);
        addChild("Drive Train", drive);
        leftEncoder = left.getEncoder();
        rightEncoder = right.getEncoder();

        setDefaultCommand(new JoystickDriveCommand(this));
    }

    public void arcadeDrive(double xSpeed, double zRotation) {
        arcadeDrive(xSpeed, zRotation, 1);
    }

    // See https://www.desmos.com/calculator/snimyobj8h for why scaleFactor gets its
    // own argument
    public void arcadeDrive(double xSpeed, double zRotation, double scaleFactor) {
        drive.arcadeDrive(Math.copySign(xSpeed * xSpeed, xSpeed) * scaleFactor,
                Math.copySign(zRotation * zRotation, zRotation) * scaleFactor, false);
    }

    public void tankDrive(double left, double right) {
        drive.tankDrive(left, right, false);
    }

    public double getLeftEncoder() {
        return leftEncoder.getPosition();
    }

    public double getRightEncoder() {
        return rightEncoder.getPosition();
    }

    public void resetEncoders() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Drive Encoder", getLeftEncoder());
    }
}
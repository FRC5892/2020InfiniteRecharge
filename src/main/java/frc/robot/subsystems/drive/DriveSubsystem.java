package frc.robot.subsystems.drive;

import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.MathUtils;
import frc.MotorSpecs;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class DriveSubsystem extends SubsystemBase {
    public final RobotContainer container;
    private final DifferentialDrive drive;
    private final CANEncoder leftEncoder, rightEncoder;

    public DriveSubsystem(RobotMap map, RobotContainer container) {
        this.container = container;
        var leftTup = MotorSpecs.makeSparkMaxGroup(map.leftDrive);
        var rightTup = MotorSpecs.makeSparkMaxGroup(map.rightDrive);
        drive = new DifferentialDrive(leftTup.first, rightTup.first);
        addChild("Drive Train", drive);
        leftEncoder = leftTup.second;
        rightEncoder = rightTup.second;

        setDefaultCommand(new JoystickDriveCommand(this));
    }

    public void arcadeDrive(double xSpeed, double zRotation) {
        arcadeDrive(xSpeed, zRotation, 1);
    }

    // See https://www.desmos.com/calculator/snimyobj8h for why scaleFactor gets its
    // own argument
    public void arcadeDrive(double xSpeed, double zRotation, double scaleFactor) {
        arcadeDrive(xSpeed, zRotation, scaleFactor, scaleFactor);
    }

    public void arcadeDrive(double xSpeed, double zRotation, double xScale, double zScale) {
        drive.arcadeDrive(MathUtils.signedSquare(xSpeed) * xScale, MathUtils.signedSquare(zRotation) * zScale, false);
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
}
package frc.robot.subsystems.wheel;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import frc.robot.subsystems.drive.DriveSubsystem;

public class WheelContextCommand extends WheelCommandGroup {
    public WheelContextCommand(DriveSubsystem drive, WheelSubsystem wheel, double speed) {
        super(drive, wheel, new ConditionalCommand(new WheelRotationControl(wheel, speed),
                new WheelPositionControl(wheel, speed), () -> wheel.colors.colorTheyWant() == null));
    }
}
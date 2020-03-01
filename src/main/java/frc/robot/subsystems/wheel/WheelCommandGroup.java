package frc.robot.subsystems.wheel;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.built_groups.BuiltSequence;
import frc.robot.subsystems.drive.AutoTankDrive;
import frc.robot.subsystems.drive.DriveSubsystem;

public class WheelCommandGroup extends BuiltSequence {
    
    public WheelCommandGroup(DriveSubsystem drive, WheelSubsystem wheel, Command command) {
        super(s -> {
           s.add(command);
           s.add(new WaitCommand(0.5));
           s.add(new AutoTankDrive(drive, 0.6, 0.6, 500).withTimeout(.2));
           s.add(new SetWheelPiston(wheel, false));
        });
    }
}
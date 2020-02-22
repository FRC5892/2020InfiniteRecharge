package frc.built_groups;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;

public class BuiltDeadline extends ParallelDeadlineGroup {
    public BuiltDeadline(Command deadline, Consumer<GroupBuilder> lambda) {
        super(deadline);
        var builder = new GroupBuilder();
        lambda.accept(builder);
        addCommands(builder.commands.toArray(new Command[0]));
    }
}
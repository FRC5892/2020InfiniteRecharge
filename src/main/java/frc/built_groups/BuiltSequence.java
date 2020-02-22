package frc.built_groups;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class BuiltSequence extends SequentialCommandGroup {
    public BuiltSequence(Consumer<GroupBuilder> lambda) {
        var builder = new GroupBuilder();
        lambda.accept(builder);
        addCommands(builder.commands.toArray(new Command[0]));
    }
}
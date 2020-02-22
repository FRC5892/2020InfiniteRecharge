package frc.built_groups;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.Command;

public final class GroupBuilder {
    GroupBuilder() {

    }

    final ArrayList<Command> commands = new ArrayList<>();

    public void add(Command c) {
        commands.add(c);
    }
}
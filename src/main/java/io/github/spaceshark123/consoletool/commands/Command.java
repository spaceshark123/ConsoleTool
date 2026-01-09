package io.github.spaceshark123.consoletool.commands;

// this interface is used to define the structure of a command
public interface Command {
    // this function is used to execute the command
    public void execute(String... arguments);

    default String description() {
        return "";
    }

    default String usage() {
        return "";
    }
}
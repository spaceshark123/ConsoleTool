package io.github.spaceshark123.consoletool.commands;

import io.github.spaceshark123.consoletool.ConsoleTool;

// this interface is used to define the structure of a command
public interface Command {
    // this function is used to execute the command
    public void execute(ConsoleTool console, String... arguments);

    default String description() {
        return "";
    }

    default String usage() {
        return "";
    }
}
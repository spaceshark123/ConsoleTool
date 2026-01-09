package io.github.spaceshark123.consoletool.commands;

import io.github.spaceshark123.consoletool.ConsoleTool;

public class ExitCommand implements Command {
    private final boolean quitOnExit; // if true, will call System.exit(0) after exiting the console to quit the program

    public ExitCommand(boolean quitOnExit) {
        this.quitOnExit = quitOnExit;
    }

    public ExitCommand() {
        this(true); // default to quitting the program
    }

    @Override
    public void execute(ConsoleTool console, String... args) {
        console.println("Exiting...");
        console.stop();
        console.finish(true);
        if (quitOnExit) {
            System.exit(0);
        }
    }

    @Override
    public String description() {
        return "Exits the console";
    }

    @Override
    public String usage() {
        return "";
    }
}
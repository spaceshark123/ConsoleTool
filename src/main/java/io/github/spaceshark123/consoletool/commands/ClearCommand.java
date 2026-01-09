package io.github.spaceshark123.consoletool.commands;

import io.github.spaceshark123.consoletool.ConsoleTool;

public class ClearCommand implements Command {

    private final ConsoleTool console;

    public ClearCommand(ConsoleTool console) {
        this.console = console;
    }

    @Override
    public void execute(String... args) {
        console.clear();
    }

    @Override
    public String description() {
        return "Clears the console screen";
    }

    @Override
    public String usage() {
        return "";
    }
}
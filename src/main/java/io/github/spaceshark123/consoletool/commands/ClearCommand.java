package io.github.spaceshark123.consoletool.commands;

import io.github.spaceshark123.consoletool.ConsoleTool;

public class ClearCommand implements Command {
    @Override
    public void execute(ConsoleTool console, String... args) {
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
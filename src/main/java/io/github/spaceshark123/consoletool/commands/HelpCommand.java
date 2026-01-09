package io.github.spaceshark123.consoletool.commands;

import io.github.spaceshark123.consoletool.ConsoleTool;
import java.util.Map;

public class HelpCommand implements Command {
    @Override
    public void execute(ConsoleTool console, String... args) {
        Map<String, Command> commands = console.getCommands();

        if (args.length == 0) {
            console.println("Available commands:");
            console.println("");

            int maxLen = commands.keySet()
                                 .stream()
                                 .mapToInt(String::length)
                                 .max()
                                 .orElse(0);

            for (Map.Entry<String, Command> entry : commands.entrySet()) {
                String name = entry.getKey();
                String desc = entry.getValue().description();
                console.println(String.format("  %-"+maxLen+"s  %s", name, desc));
            }

            console.println("");
            console.println("Type `help <command>` for detailed usage.");
            return;
        }

        String cmdName = args[0];
        Command cmd = commands.get(cmdName);

        if (cmd == null) {
            console.println("Unknown command: " + cmdName);
            return;
        }

        console.println("Command: " + cmdName);

        if (!cmd.description().isEmpty()) {
            console.println("Description: " + cmd.description());
        }

        if (!cmd.usage().isEmpty()) {
            console.println("Usage: " + cmdName + " " + cmd.usage());
        }
    }

    @Override
    public String description() {
        return "Displays available commands and usage information";
    }

    @Override
    public String usage() {
        return "[command]";
    }
}

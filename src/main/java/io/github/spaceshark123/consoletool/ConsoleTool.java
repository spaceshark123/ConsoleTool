package io.github.spaceshark123.consoletool;

import java.util.*;
import java.io.*;

import io.github.spaceshark123.consoletool.commands.ClearCommand;
import io.github.spaceshark123.consoletool.commands.Command;
import io.github.spaceshark123.consoletool.commands.HelpCommand;
import io.github.spaceshark123.consoletool.commands.ExitCommand;

// this class is used to create a console interface for the user to interact with the program (similar to a command line interface)
// it will have functions to add custom commands that execute their own code and take in their own arguments.
public class ConsoleTool {
    // the scanner object that will be used to read user input
    private final Scanner scanner;
    // the hashmap that will store the commands and their respective functions
    private final Map<String, Command> commands = new HashMap<String, Command>();
    // title of the console
    private final String title;
    private final String description;
    private final InputStream in;
    private final PrintStream out;

    private String prompt = ">> ";
    private boolean ansiEnabled = true;
    private volatile boolean running = false;

    // constructor
    public ConsoleTool(InputStream in, PrintStream out, String title, String description, boolean quitOnExit) {
        scanner = new Scanner(in);
        this.in = in;
        this.out = out;
        this.title = title;
        this.description = description;

        // initially set ansiEnabled to false if on windows (for compatibility)
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            ansiEnabled = false;
        }

        // add basic commands (help, clear, exit)
        addCommand("help", new HelpCommand());
        addCommand("clear", new ClearCommand());
        addCommand("exit", new ExitCommand(quitOnExit));

        clear();
    }

    public ConsoleTool(InputStream in, PrintStream out, String title, String description) {
        this(in, out, title, description, true);
    }

    public ConsoleTool(InputStream in, PrintStream out, String title) {
        this(in, out, title, "", true);
    }

    public ConsoleTool(InputStream in, PrintStream out, String title, boolean quitOnExit) {
        this(in, out, title, "", quitOnExit);
    }

    public void setAnsiEnabled(boolean enabled) {
        this.ansiEnabled = enabled;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    // this function is used to add a command to the hashmap
    public void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    // this function is used to get the commands hashmap as a read-only map
    public Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(commands);
    }

    // this function is used to remove a command from the hashmap
    public void removeCommand(String commandName) {
        commands.remove(commandName);
    }

    // this function is used to start the console interface
    public void start() {
        // the main loop of the console interface
        running = true;
        while (running) {
            // read the user input
            String input = input();
            if (input.trim().isEmpty()) {
                continue;
            }
            // tokenize the input string
            String[] inputArray = tokenize(input).toArray(new String[0]);
            String commandName = inputArray[0];
            String[] arguments = Arrays.copyOfRange(inputArray, 1, inputArray.length);
            // check if the command exists in the hashmap
            Command command = commands.get(commandName);
            if (command == null) {
                println("Error: Command not found");
                continue;
            }
            // execute the command
            try {
                command.execute(this, arguments);
            } catch (IllegalArgumentException e) {
                println("Error: " + e.getMessage());
                if (!command.usage().isEmpty()) {
                    println("Usage: " + commandName + " " + command.usage());
                }
            } catch (Exception e) {
                println("Error: " + e.getMessage());
            }

        }
    }

    public void stop() {
        running = false;
    }

    // utility function to tokenize input string while respecting quoted substrings
    private static List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder current = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ' ' && !inQuotes) {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
            } else {
                current.append(c);
            }
        }
        if (current.length() > 0)
            tokens.add(current.toString());
        return tokens;
    }

    // this function is used to close the console interface
    public void finish() {
        // NO scanner.close()
    }

    // finish method that allows closing streams (recommended to avoid resource
    // leaks)
    public void finish(boolean closeStreams) {
        if (closeStreams) {
            scanner.close();
        }
    }

    public void println(Object output) {
        out.println(output);
    }

    public void println(int output) {
        out.println(output);
    }

    public void println(double output) {
        out.println(output);
    }

    public void println(int[] arr) {
        out.println(Arrays.toString(arr));
    }

    public void println(double[] arr) {
        out.println(Arrays.toString(arr));
    }

    public void println(Object[] arr) {
        out.println(Arrays.toString(arr));
    }

    public String input() {
        out.print(prompt);
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            // This exception occurs when the input stream is closed.
            // We handle it by stopping the console.
            stop();
            return "";
        }
    }

    public void clear() {
        if (!ansiEnabled) {
            // print multiple newlines as a reliable fallback
            // ANSI codes work on Windows 10+ with modern terminals, but
            // this ensures compatibility with older systems
            for (int i = 0; i < 50; i++) {
                out.println();
            }
        } else {
            // Use ANSI escape codes to clear the console
            out.print("\033[H\033[2J");
            out.flush();
        }
        out.println(title);
        if (!description.isEmpty()) {
            out.println(description);
        }
        out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }

    public void progressBar(int width, String title, int current, int total, String subtitle) {
        if (total <= 0) {
            throw new IllegalArgumentException("Total must be greater than 0");
        }
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be greater than 0");
        }
        if (current < 0 || current > total) {
            throw new IllegalArgumentException("Current must be between 0 and total");
        }
        String filled = "█";
        String unfilled = "░";
        double fill = (double) current / total;
        if (fill >= 0 && fill <= 1) {
            // set progress bar
            int fillAmount = (int) Math.ceil(fill * width);
            StringBuilder bar = new StringBuilder();
            bar.append(title).append(": ").append(filled.repeat(fillAmount)).append(unfilled.repeat(width - fillAmount))
                    .append(" ").append(current).append("/").append(total).append(" ").append(subtitle).append(" ");
            if (current == total) {
                bar.append("\n");
            } else {
                bar.append("\r");
            }
            out.print(bar.toString());
        }
    }
}

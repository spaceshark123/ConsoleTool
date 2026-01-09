package io.github.spaceshark123.consoletool;

import io.github.spaceshark123.consoletool.commands.Command;
import io.github.spaceshark123.consoletool.util.Args;

public class ConsoleToolTest {
    public static void main(String[] args) throws Exception {
        ConsoleTool console = new ConsoleTool(System.in, System.out, "Test Console");
        console.addCommand("test", new Command() {
            public void execute(ConsoleTool console, String... arguments) {
                for(int i = 0; i < 1000; i++) {
                    console.println("Test " + i);
                    console.progressBar(15, "Progress: ", i, 1000, " subtext");
                }
            }

            public String description() {
                return "A test command that prints numbers and a progress bar";
            }

            public String usage() {
                return "";
            }
        });
        console.addCommand("add", new Command() {
            public void execute(ConsoleTool console, String... arguments) {
                Args.require(arguments, 2);
                int a = Args.parseInt(arguments[0], "first number");
                int b = Args.parseInt(arguments[1], "second number");
                console.println(a + b);
            }

            public String description() {
                return "Adds two integers";
            }

            public String usage() {
                return "<int1> <int2>";
            }
        });
        console.addCommand("sub", new Command() {
            public void execute(ConsoleTool console, String... arguments) {
                Args.require(arguments, 2);
                int a = Args.parseInt(arguments[0], "first number");
                int b = Args.parseInt(arguments[1], "second number");
                console.println(a - b);
            }

            public String description() {
                return "Subtracts two integers";
            }

            public String usage() {
                return "<int1> <int2>";
            }
        });
        console.start();
    }
}

import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        ConsoleTool console = new ConsoleTool(System.in, System.out, "Test Console\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.addCommand("test", new ConsoleTool.Command() {
            public void execute(String... arguments) {
                for(int i = 0; i < 1000; i++) {
                    console.Output("Test " + i);
                    console.ProgressBar(15, "Progress: ", i, 1000, " subtext");
                }
            }
        });
        console.addCommand("add", new ConsoleTool.Command() {
            public void execute(String... arguments) {
                if (arguments.length == 2) {
                    try {
                        int a = Integer.parseInt(arguments[0]);
                        int b = Integer.parseInt(arguments[1]);
                        console.Output(a + b);
                    } catch (NumberFormatException e) {
                        console.Output("Error: Invalid arguments");
                    }
                } else {
                    console.Output("Error: Invalid arguments");
                }
            }
        });
        console.addCommand("sub", new ConsoleTool.Command() {
            public void execute(String... arguments) {
                if (arguments.length == 2) {
                    try {
                        int a = Integer.parseInt(arguments[0]);
                        int b = Integer.parseInt(arguments[1]);
                        console.Output(a - b);
                    } catch (NumberFormatException e) {
                        console.Output("Error: Invalid arguments");
                    }
                } else {
                    console.Output("Error: Invalid arguments");
                }
            }
        });
        console.addCommand("clear", new ConsoleTool.Command() {
            public void execute(String... arguments) {
                console.Clear();
                console.Output("Console cleared");
            }
        });
        console.addCommand("help", new ConsoleTool.Command() {
            public void execute(String... arguments) {
                console.Output("Commands:");
                console.Output("test - Test command");
                console.Output("add <a> <b> - Add two numbers");
                console.Output("sub <a> <b> - Subtract two numbers");
                console.Output("help - Show this help message");
                console.Output("clear - Clear the console");
                console.Output("exit - Exit the console");
            }
        });
        console.addCommand("exit", new ConsoleTool.Command() {
            public void execute(String... arguments) {
                console.finish();
                System.exit(0);
            }
        });
        console.start();
    }
}

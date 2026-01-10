import io.github.spaceshark123.consoletool.commands.Command;
import io.github.spaceshark123.consoletool.util.Args;

/**
 * Example usage of ConsoleTool to create a simple console application with commands.
 */
class ConsoleToolExample {
    public static void main(String[] args) throws Exception {
        ConsoleTool console = new ConsoleTool(System.in, System.out, "Example Console");

        console.addCommand("test", new Command() {
            public void execute(ConsoleTool console, String... arguments) {
                for(int i = 0; i <= 1000; i++) {
                    console.progressBar(15, "Progress: ", i, 1000, " subtext");
                }
                console.println("Done!");
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
                Args.min(arguments, 2); // require at least two arguments
                int a = Args.parseInt(arguments[0], "first number");
                int b = Args.parseInt(arguments[1], "second number");
                int c = Args.optionalInt(arguments, 2, 0, "third number"); // optional third argument with default value 0
                console.println(a + b + c);
            }

            public String description() {
                return "Adds two or three integers";
            }

            public String usage() {
                return "<int1> <int2> [int3]";
            }
        });
        console.addCommand("sub", new Command() {
            public void execute(ConsoleTool console, String... arguments) {
                Args.require(arguments, 2); // require exactly two arguments
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

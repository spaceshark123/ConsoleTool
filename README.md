# ConsoleTool

`ConsoleTool` is a Java class designed to create a console-based interface for interacting with a program, similar to a command-line interface (CLI). It allows for custom commands to be added, which can execute their own code and take in their own arguments. This tool can be useful for building interactive applications that require a simple, text-based user interface.

## Features

- **Custom Commands**: Easily define and add custom commands that the console can execute. Commands can accept arguments and execute corresponding actions.
- **Input and Output Handling**: Provides methods to read user input and print output to the console.
- **Stream Configuration**: Allows configuring input and output streams for flexible usage scenarios.
- **Clear Screen**: Functionality to clear the console screen and display a title.
- **Error Handling**: Basic error handling for unrecognized commands.

## How to Use

1. **Initialization**: Create an instance of `ConsoleTool` by providing an input stream, output stream, and a title for the console.
2. **Adding Commands**: Use the `addCommand` method to add custom commands. A command must implement the `Command` interface and define the `execute` method.
3. **Starting the Console**: Call the `start` method to begin the console interface. The program will then wait for user input to execute commands.
4. **Stopping the Console**: Use the `finish` method to close the console and release resources.

## Example

Below is a simple example demonstrating how to use `ConsoleTool`:

```java
import java.io.InputStream;
import java.io.PrintStream;

public class ConsoleToolExample {
    public static void main(String[] args) {
        ConsoleTool console = new ConsoleTool(System.in, System.out, "My Console Tool");

        console.addCommand("greet", new ConsoleTool.Command() {
            @Override
            public void execute(String... arguments) {
                if (arguments.length > 0) {
                    console.Output("Hello, " + String.join(" ", arguments) + "!");
                } else {
                    console.Output("Hello, World!");
                }
            }
        });

        console.addCommand("exit", new ConsoleTool.Command() {
            @Override
            public void execute(String... arguments) {
                console.Output("Exiting the console...");
                console.finish();
                System.exit(0);
            }
        });

        console.start();
    }
}
```

In this example:

- The greet command is added, which prints a greeting message. It can take arguments to customize the greeting.
- The exit command is added to terminate the console session.

# Methods
### Constructor

```java
ConsoleTool(InputStream in, PrintStream out, String title)

    in: The input stream for reading user input.
    out: The output stream for writing output.
    title: The title to display at the top of the console.
```

### addCommand

```java
void addCommand(String commandName, Command command)

    commandName: The name of the command as it will be typed by the user.
    command: An implementation of the Command interface defining the action to be executed.
```

### start

```java
void start()
```

Starts the console interface, reading user input and executing corresponding commands in a loop.

### finish

```java
void finish()
```

Stops the console interface and closes the input scanner.

### Output

```java
void Output(Object output)
void Output(int output)
void Output(double output)
void Output(int[] arr)
void Output(double[] arr)
void Output(Object[] arr)
```

Various overloaded methods to print different types of output to the console.

### Input

```java
String Input()
```

Prompts the user for input and returns the entered string.

### Clear

```java
void Clear()
```

Clears the console screen and prints the console title.
package io.github.spaceshark123.consoletool.util;

// utility class for argument parsing and validation in commands
public final class Args {

    private Args() {
    }

    public static void require(String[] args, int count) {
        if (args.length < count) {
            throw new IllegalArgumentException(
                    "Expected at least " + count + " arguments, got " + args.length);
        }
    }

    public static int parseInt(String value, String name) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Invalid integer for " + name + ": " + value);
        }
    }

    public static double parseDouble(String value, String name) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Invalid number for " + name + ": " + value);
        }
    }

    public static long parseLong(String value, String name) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Invalid long integer for " + name + ": " + value);
        }
    }

    public static boolean parseBoolean(String value, String name) {
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(value);
        }
        throw new IllegalArgumentException(
                "Invalid boolean for " + name + ": " + value);
    }
}

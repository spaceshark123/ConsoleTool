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

    public static void max(String[] args, int max) {
        if (args.length > max) {
            throw new IllegalArgumentException(
                    "Expected at most " + max + " arguments, got " + args.length);
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

    public static String parseString(String value, String name) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(
                    "Invalid string for " + name + ": " + value);
        }
        return value;
    }

    public static int optionalInt(String[] args, int index, int defaultValue, String name) {
        if (index >= args.length) {
            return defaultValue;
        }
        return parseInt(args[index], name);
    }

    public static double optionalDouble(String[] args, int index, double defaultValue, String name) {
        if (index >= args.length) {
            return defaultValue;
        }
        return parseDouble(args[index], name);
    }

    public static long optionalLong(String[] args, int index, long defaultValue, String name) {
        if (index >= args.length) {
            return defaultValue;
        }
        return parseLong(args[index], name);
    }

    public static boolean optionalBoolean(String[] args, int index, boolean defaultValue, String name) {
        if (index >= args.length) {
            return defaultValue;
        }
        return parseBoolean(args[index], name);
    }

    public static String optionalString(String[] args, int index, String defaultValue, String name) {
        if (index >= args.length) {
            return defaultValue;
        }
        return parseString(args[index], name);
    }

}

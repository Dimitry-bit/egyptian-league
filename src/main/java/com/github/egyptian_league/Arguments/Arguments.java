package com.github.egyptian_league.Arguments;

import java.util.Arrays;

public class Arguments {
    private static String[] arguments;

    private Arguments() {
    }

    public static void initArgs(String[] args) {
        arguments = args;
    }

    public static String[] getArgument(String arg, int argParams) {
        if (!isInitialized()) {
            throw new IllegalStateException("Arguments must be initialized first");
        }

        for (int i = 0; i < arguments.length - argParams; ++i) {
            if (arg.equals(arguments[i])) {
                return Arrays.copyOfRange(arguments, i, i + argParams + 1);
            }
        }

        return null;
    }

    private static boolean isInitialized() {
        return (arguments != null);
    }
}

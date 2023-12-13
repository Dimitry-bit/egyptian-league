package com.github.egyptian_league;

public class InvalidYearException extends Exception {

    // FIXME: Take a message string instead of x, and y
    public InvalidYearException(int x, int y) {
        // FIXME: What is the purpose of x, and y ?
        super("the year is less than current year");
    }
}

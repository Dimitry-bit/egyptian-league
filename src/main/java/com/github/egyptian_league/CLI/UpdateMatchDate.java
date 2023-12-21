package com.github.egyptian_league.CLI;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;
import static com.github.egyptian_league.CLI.UpdateMatch.CurrentMatch;

public class UpdateMatchDate extends MenuItem {
    public UpdateMatchDate(String name, MenuItem Back) {
        super(name, Back);
    }

    @Override
    public boolean update() {
        Scanner in = new Scanner(System.in);

        boolean isValid = true;
        LocalDateTime parsedDate = null;
        do {
            clearCli();
            if (!isValid) {
                System.out.println("Invalid Date and/or Time format");
                isValid = true;
            }
            System.out.println("Enter the new date of the match in YYYY-MM-DD format");
            String Date = in.nextLine();

            if (Date.length() == 1 && Date.charAt(0) == 'x')
            {
                back();
                return true;
            }

            System.out.println("Enter the time of the match in HH:MM format");
            String Time = in.nextLine();

            if (Time.length() == 1 && Time.charAt(0) == 'x')
            {
                back();
                return true;
            }

            String ToBeParsed = Date + "T" + Time + ":00";

            try {
                parsedDate = LocalDateTime.parse(ToBeParsed);
            } catch (DateTimeParseException e) {
                isValid = false;
            }

        }while (!isValid);

        CurrentMatch.setDate(parsedDate);
        back();
        return true;
    }
}

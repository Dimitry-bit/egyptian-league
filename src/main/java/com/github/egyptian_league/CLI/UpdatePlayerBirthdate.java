package com.github.egyptian_league.CLI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;
import static com.github.egyptian_league.CLI.ChoosePlayer.CurrentPlayer;

public class UpdatePlayerBirthdate extends MenuItem {
    public UpdatePlayerBirthdate(String name, UpdatePlayerInfo Back) {
        super(name, Back);
    }

    @Override
    public boolean update() {

        Scanner in = new Scanner(System.in);

        boolean isValid = true;
        LocalDate BirthDate = null;
        do {
            clearCli();
            if (!isValid) {
                System.out.println("Invalid Date format");
                isValid = true;
            }
            System.out.println("Enter the new player's birthdate in YYYY-MM-DD format");
            String Date = in.nextLine();

            try {
                BirthDate = LocalDate.parse(Date);
            } catch (DateTimeParseException e) {
                isValid = false;
            }

        }while (!isValid);

        CurrentPlayer.setBirthDay(BirthDate);

        back();
        return true;
    }
}

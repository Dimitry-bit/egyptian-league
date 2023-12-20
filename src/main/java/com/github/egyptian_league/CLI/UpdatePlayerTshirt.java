package com.github.egyptian_league.CLI;

import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;
import static com.github.egyptian_league.CLI.UpdateTeam.CurrentTeam;

public class UpdatePlayerTshirt extends MenuItem {
    public UpdatePlayerTshirt(String name, MenuItem Back) {
        super(name, Back);
    }

    @Override
    public boolean update() {

        Scanner in = new Scanner(System.in);

        boolean isValid = true;
        do {
            clearCli();

            if (!isValid){
                System.out.println("Please enter a valid integer...");
                isValid = true;
            }

            System.out.println("Enter the new player's Tshirt no:");

            String input = in.nextLine();
            int number;
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                isValid = false;
            }
        }while (!isValid);

        back();

        currentMenuItem.setName("Chosen Team is " + CurrentTeam.getName());



        return true;
    }
}

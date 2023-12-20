package com.github.egyptian_league.CLI;

import com.github.egyptian_league.Models.Position;

import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;
import static com.github.egyptian_league.CLI.ChoosePlayer.CurrentPlayer;

public class UpdatePlayerPosition extends MenuItem{
    public UpdatePlayerPosition(String name, MenuItem Back) {
        super(name, Back);
    }

    @Override
    public boolean update(){

        Scanner in = new Scanner(System.in);

        boolean isValid = true;
        int PosChoice;

        do {
            clearCli();
            System.out.println("Choose new player's position");

            if (!isValid) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + Position.values().length);
            }

            Position position;
            for (int j = 0; j < Position.values().length; j++) {
                // Perform operations with each enum constant
                System.out.printf("%d] %s\n", j+1, Position.values()[j]);
            }

            String eofTerminal = "\033[9999H";
            System.out.print(eofTerminal);

            System.out.println("Enter your choice: ");

            String input = in.nextLine();

            try {
                PosChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                PosChoice = 0;

            }

            if ((PosChoice < 1 || PosChoice > Position.values().length)) {
                isValid = false;
            }
            else{
                isValid = true;
            }

        }while(!isValid);

        CurrentPlayer.setPosition(Position.values()[PosChoice-1]);

        back();
        return true;
    }
}

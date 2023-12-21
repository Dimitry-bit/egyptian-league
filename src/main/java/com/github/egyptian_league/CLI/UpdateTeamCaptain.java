package com.github.egyptian_league.CLI;

import com.github.egyptian_league.ApplicationRepository;

import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;
import static com.github.egyptian_league.CLI.UpdateTeam.CurrentTeam;

public class UpdateTeamCaptain extends MenuItem {
    public UpdateTeamCaptain(String name, MenuItem Back) {
        super(name, Back);
    }

    public boolean update() {

        Scanner in = new Scanner(System.in);


        int size = CurrentTeam.getPlayers().size();
        boolean isValid = true;
        int choice;
        do {
            clearCli();

            if (!isValid) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + size);
            }

            System.out.println("\nChoose the player you desire to appoint new captain:");

            for (int i = 0; i < size; i++) {
                System.out.printf("%d] %s \n", i + 1, CurrentTeam.getPlayers().get(i).getName());
            }

            System.out.println("x] Back");

            String eofTerminal = "\033[9999H";
            System.out.print(eofTerminal);

            System.out.println("Enter your choice: ");

            String input = in.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = (int) input.charAt(0);
            }


            if (choice >= 1 && choice <= size || choice == 'x') {
                isValid = true;
            } else {
                isValid = false;
            }
        } while (!isValid);


        if (choice == 'x') {
            back();
            return true;
        }


        clearCli();
        CurrentTeam.setCaptain(CurrentTeam.getPlayers().get(choice - 1));
        System.out.printf("Team Captain appointed to %s Successfully. Press Enter key to continue...\n",CurrentTeam.getCaptain().getName());
        try
        {
            System.in.read();
            in.nextLine();
        }
        catch(Exception e)
        {}

        back();
        return true;
    }

}

package com.github.egyptian_league.CLI;

import com.github.egyptian_league.Models.Player;

import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;
import static com.github.egyptian_league.CLI.UpdateTeam.CurrentTeam;

public class ChoosePlayer extends MenuItem{

    public static Player CurrentPlayer;

    public static boolean isAddingPlayer = false;
    public ChoosePlayer(String name, MenuItem Back) {
        super(name, Back);
    }

    @Override
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

            System.out.println("\nChoose the player you desire to update:");

            System.out.println("Choose a player:");

            int i;
            for (i = 0; i < size; i++) {
                System.out.printf("%d] %s \n",i+1,CurrentTeam.getPlayers().get(i).getName());
            }

            if (size < 11) {
                System.out.printf("%d] Add new player\n", i + 1);
                isAddingPlayer = true;
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


            if (choice >= 1 && choice <= size || choice == 'x' || ((choice == size + 1) && isAddingPlayer) ) {
                isValid = true;
            }
            else{
                isValid = false;
            }

            //if ((choice < 1 || choice > size) && choice != 'x' && ((choice != size + 1) || isAddingPlayer))
        }while(!isValid);

        if (choice == size + 1)
        {
            isAddingPlayer = true;
        }
        else {
            isAddingPlayer = false;
        }

        if (choice == 'x'){
            back();
            return true;
        }

        currentMenuItem = currentMenuItem.getSubMenus().get(0);

        if(!isAddingPlayer)
        {
        CurrentPlayer = CurrentTeam.getPlayers().get(choice-1);
        currentMenuItem.setName("Chosen Player is " + CurrentPlayer.getName());
        }

        return true;
    }
}

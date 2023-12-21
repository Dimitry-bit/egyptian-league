package com.github.egyptian_league.CLI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Team;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;
import static com.github.egyptian_league.CLI.UpdateMatch.CurrentMatch;

public class UpdateMatchTeam extends MenuItem {
    boolean isHome;
    public UpdateMatchTeam(String name, MenuItem Back, boolean isHome) {
        super(name, Back);
        this.isHome = isHome;
    }

    @Override
    public boolean update() {

        Iterator<Team> iterator = ApplicationRepository.getRepository().getTeamsIterator();

        ArrayList<Team> Teams = new ArrayList<>();

        Scanner in = new Scanner(System.in);


        while (iterator.hasNext()) {
            Team team = iterator.next();
            Teams.add(team);
        }
        int size = Teams.size();


        boolean isValid = true;
        int choice;
        do {
            clearCli();

            if (!isValid) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + size);
            }

            if (isHome) {
                System.out.println("\nChoose the home team you desire to change to:");
            }
            else{
                System.out.println("\nChoose the away team you desire to change to:");
            }

            for (int i = 0; i < size; i++) {
                System.out.printf("%d] %s\n", i + 1, Teams.get(i).getName());
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



            if ((choice < 1 || choice > size) && choice != 'x') {
                isValid = false;
            }
            else{
                isValid = true;
            }
        }while(!isValid);


        if (choice != 'x') {
            clearCli();

            if (isHome) {
                System.out.printf("Home Team Updated to %s Successfully. Press Enter key to continue...\n",Teams.get(choice - 1).getName());

                CurrentMatch.setHomeTeam(Teams.get(choice-1));
            }
            else {
                System.out.printf("Away Team Updated to %s Successfully. Press Enter key to continue...\n",Teams.get(choice - 1).getName());

                CurrentMatch.setAwayTeam(Teams.get(choice-1));
            }

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

        back();
        return true;
    }
}

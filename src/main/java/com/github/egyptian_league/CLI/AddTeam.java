package com.github.egyptian_league.CLI;

import com.github.egyptian_league.*;
import com.github.egyptian_league.Models.Player;
import com.github.egyptian_league.Models.Position;
import com.github.egyptian_league.Models.Team;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;

public class AddTeam extends MenuItem{


    public AddTeam(String name, MenuItem Back) {
        super(name, Back);
    }

    public boolean update (){

        clearCli();

        Scanner in = new Scanner(System.in);

        //premature exit not yet implemented
        System.out.println("Enter 'x' at any point to exit adding team. HOWEVER exiting prematurely will DISCARD all your entered data");


        System.out.println("Enter the Team Name:");

        String TeamName = in.nextLine();

        if (TeamName.length() == 1 && TeamName.charAt(0) == 'x')
        {
            back();
            return true;
        }

        Team team = new Team(TeamName);

        ArrayList<Player> players = new ArrayList<>();

        int playercount = 1;
        do {
            clearCli();
            if (playercount > 11 || playercount <= 0)
            {
                System.out.println("Enter a valid number of players..");
            }
            System.out.println("Enter no. of players to add (MAX 11):");

            String input = in.nextLine();
            try {
                playercount = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                if (input.length() == 1 && input.charAt(0) == 'x'){
                    back();
                    return true;
                }
                else{
                    playercount = 0;
                }
            }

        }while (playercount > 11 || playercount <= 0);



        for (int i = 0; i < playercount; i++) {

            clearCli();
            System.out.printf("[%d/%d] Enter Player %d's name:\n", i+1, playercount , i+1);
            String PlayerName = in.nextLine();

            if (PlayerName.length() == 1 && PlayerName.charAt(0) == 'x')
            {
                back();
                return true;
            }

            boolean isValid = true;
            LocalDate BirthDate = null;
            do {
                clearCli();
                if (!isValid) {
                    System.out.println("Invalid Date format");
                    isValid = true;
                }
                System.out.println("Enter the player's birthdate in YYYY-MM-DD format");
                String Date = in.nextLine();

                try {
                    BirthDate = LocalDate.parse(Date);
                } catch (DateTimeParseException e) {
                    if (Date.length() == 1 && Date.charAt(0) == 'x')
                    {
                        back();
                        return true;
                    }
                    else {
                        isValid = false;
                    }
                }

            }while (!isValid);

            //take position from user
            isValid = true;
            int PosChoice;

            do {
                clearCli();
                System.out.printf("\n[%d/%d] Choose Player %d's position\n", i+1, playercount, i+1);
                if (!isValid) {
                    System.out.println("\nInvalid choice. Please enter a number between 1 and " + Position.values().length);
                }


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
                    if (input.length() == 1 && input.charAt(0) == 'x'){
                        back();
                        return true;
                    }
                    else {
                        PosChoice = 0;
                    }
                }

                if (PosChoice < 1 || PosChoice > Position.values().length) {
                    isValid = false;
                }
                else{
                    isValid = true;
                }

            }while(!isValid);


            int TshirtNumber = 0;
            isValid = true;
            do {
                clearCli();

                if (!isValid)
                {
                    System.out.println("Please Enter a valid integer...");
                    isValid = true;
                }
                System.out.printf("[%d/%d]Enter player %d's T-shirt number:\n", i + 1, playercount, i + 1);


                String input = in.nextLine();

                try {
                    TshirtNumber = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    if (input.length() == 1 && input.charAt(0) == 'x') {
                        back();
                        return true;
                    } else {
                        isValid = false;
                    }
                }
            }while (!isValid);

            Player player = new Player(PlayerName, team.Id, BirthDate, Position.values()[PosChoice-1], TshirtNumber);


            players.add(player);
            team.addPlayer(player.Id);

        }

        clearCli();



        boolean isValid = true;
        int CapChoice;
        do {
            clearCli();
            System.out.println("\nChoose captain:");
            if (!isValid) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + players.size());
            }


            for (int j = 0; j < players.size(); j++) {
                // Perform operations with each enum constant
                System.out.printf("%d] %s %s\n", j+1, players.get(j).getName(), players.get(j).getPosition());
            }

            String eofTerminal = "\033[9999H";
            System.out.print(eofTerminal);

            System.out.println("Enter your choice: ");

            String input = in.nextLine();

            try {
                CapChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                if (input.length() == 1 && input.charAt(0) == 'x'){
                    back();
                    return true;
                }
                else {
                    CapChoice = 0;
                }
            }

            if ((CapChoice < 1 || CapChoice > players.size())) {
                isValid = false;
            }
            else{
                isValid = true;
            }

        }while(!isValid);

        //NOTE : Adds captain UUID not yet in repo
        team.setCaptain(players.get(CapChoice - 1));

        clearCli();

        System.out.printf("Team Name : %s\nTeam Captain : %s\nPlayers :\n", team.getName(), players.get(CapChoice - 1).getName());

        for (int i = 0; i < playercount; i++) {
            System.out.printf("%d]%s\n", i+1, players.get(i).getName());
        }

        char c;
        do {
            System.out.println("Save this team? [y to save, n to DISCARD!!! all entered data]: ");
            c = in.nextLine().charAt(0);
        }while(c != 'y' && c != 'Y' && c != 'n' && c != 'N');

        if (c == 'y' || c == 'Y')
        {
            ApplicationRepository.getRepository().putTeam(team);

            for (Player p : players) {
                ApplicationRepository.getRepository().putPlayer(p);
            }

            back();
        }
        else {
            back();
        }

        return true;
    }


}

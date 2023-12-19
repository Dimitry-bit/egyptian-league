package com.github.egyptian_league.CLI;

import com.github.egyptian_league.Player;
import com.github.egyptian_league.Position;
import com.github.egyptian_league.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;

public class AddTeam extends MenuItem{


    public AddTeam(String name, MenuItem Back) {
        super(name, Back);
    }

    public boolean update (){

        clearCli();

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the Team Name:");

        String TeamName = in.nextLine();

        Team team = new Team(TeamName);

        ArrayList<Player> players = new ArrayList<>();

        for (int i = 0; i < 11; i++) {

            clearCli();
            System.out.printf("[%d/11] Enter Player %d's name:\n", i+1 , i+1);
            String PlayerName = in.nextLine();

            //take position from user
            boolean isValid = true;
            int PosChoice;

            do {
                clearCli();
                System.out.printf("\n[%d/11] Choose Player %d's position\n", i+1, i+1);
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

                char inputChar = in.nextLine().charAt(0);

                if (Character.isDigit(inputChar)) {
                    PosChoice = Character.getNumericValue(inputChar);
                } else {
                    PosChoice = inputChar;
                }

                if ((PosChoice < 1 || PosChoice > Position.values().length) && PosChoice != 'x') {
                    isValid = false;
                }
                else{
                    isValid = true;
                }

            }while(!isValid);

            clearCli();

            System.out.printf("Enter player %d's T-shirt number:\n", i+1);

            int TshirtNumber = Integer.parseInt(in.nextLine());

            Player player = new Player(PlayerName, team.getUuid(), Position.values()[PosChoice-1], TshirtNumber);


            players.add(player);
            team.addPlayer(player.getId());

        }

        clearCli();



        boolean isValid = true;
        int CapChoice;
        do {
            clearCli();
            System.out.println("Choose captain:");
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

            CapChoice = Integer.parseInt(in.nextLine());

            if ((CapChoice < 1 || CapChoice > Position.values().length) && CapChoice != 'x') {
                isValid = false;
            }
            else{
                isValid = true;
            }

        }while(!isValid);

        team.setCaptain(players.get(CapChoice-1).getId());

        return true;
    }


}

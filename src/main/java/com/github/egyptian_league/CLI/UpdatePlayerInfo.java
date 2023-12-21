package com.github.egyptian_league.CLI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Player;
import com.github.egyptian_league.Models.Position;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;
import static com.github.egyptian_league.CLI.ChoosePlayer.isAddingPlayer;
import static com.github.egyptian_league.CLI.UpdateTeam.CurrentTeam;

public class UpdatePlayerInfo extends MenuItem {
    public UpdatePlayerInfo(String name, MenuItem Back) {
        super(name, Back);
    }


    @Override
    public boolean update() {
        if (!isAddingPlayer){return super.update();}
        else{

            Scanner in = new Scanner(System.in);

            clearCli();
            System.out.println("Enter 'x' at any point to exit adding team. HOWEVER exiting prematurely will DISCARD all your entered data");

            System.out.printf("Enter Player's name:\n");

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
                System.out.printf("\nChoose Player's position\n");
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
                System.out.printf("Enter player's T-shirt number:\n");


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


            Player player = new Player(PlayerName, CurrentTeam.Id, BirthDate, Position.values()[PosChoice-1], TshirtNumber);

            ApplicationRepository.getRepository().putPlayer(player);
            CurrentTeam.addPlayer(player.Id);

            back();
            return true;
        }
    }
}

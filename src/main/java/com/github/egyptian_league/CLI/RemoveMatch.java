package com.github.egyptian_league.CLI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Match;
import com.github.egyptian_league.Models.Referee;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;

public class RemoveMatch extends MenuItem{
    public RemoveMatch(String name, MenuItem Back) {
        super(name, Back);
    }



    @Override
    public boolean update() {

        Iterator<Match> iterator = ApplicationRepository.getRepository().getMatchesIterator();

        ArrayList<Match> Matches = new ArrayList<>();

        Scanner in = new Scanner(System.in);


        while (iterator.hasNext()) {
            Match match = iterator.next();
            Matches.add(match);
        }
        int size = Matches.size();

        boolean isValid = true;
        int choice;
        do {
            clearCli();

            if (!isValid) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + size);
            }

            System.out.println("\nChoose the match you desire to remove. WARNING!! this is irreversible:");

            if (size != 0) {
                for (int i = 0; i < size; i++) {
                    System.out.printf("%d] %s v.s. %s in %s on %s\n",
                            i + 1,
                            Matches.get(i).getHomeTeam().getName(),
                            Matches.get(i).getAwayTeam().getName(),
                            Matches.get(i).getStadium().getName(),
                            Matches.get(i).getDateTime());

                }
            }
            else {
                System.out.println("There are no matches found....");
            }
            System.out.println("x]Back");

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

            System.out.printf("Match %s v.s. %s removed Successfully. Press Enter key to continue...\n",Matches.get(choice - 1).getHomeTeam().getName(), Matches.get(choice-1).getAwayTeam().getName());

            ApplicationRepository.getRepository().removeMatch(Matches.get(choice - 1));

            try
            {
                System.in.read();
                in.nextLine();
            }
            catch(Exception e)
            {}

            back();
        }
        else{
            back();
        }

        return true;
    }
}

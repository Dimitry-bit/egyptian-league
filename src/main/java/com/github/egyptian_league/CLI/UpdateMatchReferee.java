package com.github.egyptian_league.CLI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Referee;
import com.github.egyptian_league.Models.Stadium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;
import static com.github.egyptian_league.CLI.UpdateMatch.CurrentMatch;

public class UpdateMatchReferee extends MenuItem {
    public UpdateMatchReferee(String name, MenuItem Back) {
        super(name, Back);
    }

    @Override
    public boolean update() {
        Iterator<Referee> iterator = ApplicationRepository.getRepository().getRefereesIterator();

        ArrayList<Referee> Referees = new ArrayList<>();

        Scanner in = new Scanner(System.in);


        while (iterator.hasNext()) {
            Referee referee = iterator.next();
            Referees.add(referee);
        }
        int size = Referees.size();

        boolean isValid = true;
        int choice;
        do {
            clearCli();

            if (!isValid) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + size);
            }

            System.out.println("\nChoose the new referee for the match:");

            for (int i = 0; i < size; i++) {
                System.out.printf("%d] %s\n", i + 1, Referees.get(i).getName());
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
            System.out.printf("Refere Updated to %s Successfully. Press Enter key to continue...\n",Referees.get(choice - 1).getName());

            CurrentMatch.setReferee(Referees.get(choice - 1));

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

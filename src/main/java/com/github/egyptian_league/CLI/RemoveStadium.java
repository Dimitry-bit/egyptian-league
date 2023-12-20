package com.github.egyptian_league.CLI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Stadium;
import com.github.egyptian_league.Models.Team;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;

public class RemoveStadium extends MenuItem {
    public RemoveStadium(String name, MenuItem Back) {
        super(name,Back);
    }

    @Override
    public boolean update() {
        Iterator<Stadium> iterator = ApplicationRepository.getRepository().getStadiumsIterator();

        ArrayList<Stadium> Stadiums = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        int size = 0;
        while (iterator.hasNext()) {
            Stadium stadium = iterator.next();
            Stadiums.add(stadium);
            size++;
        }

        boolean isValid = true;
        int choice;
        do {
            clearCli();

            if (!isValid) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + size);
            }

            System.out.println("\nChoose the stadium you desire to remove. WARNING!! this is irreversible:");

            if (size != 0) {
                for (int i = 0; i < size; i++) {
                    System.out.printf("%d] %s\n", i + 1, Stadiums.get(i).getName());
                }
            }
            else {
                System.out.println("There are no stadiums found....");
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
            System.out.printf("Stadium %s removed Successfully. Press Enter key to continue...\n",Stadiums.get(choice - 1).getName());

            ApplicationRepository.getRepository().removeStadium(Stadiums.get(choice - 1));

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

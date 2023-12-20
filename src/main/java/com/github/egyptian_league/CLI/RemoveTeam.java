package com.github.egyptian_league.CLI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Position;
import com.github.egyptian_league.Models.Team;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;

public class RemoveTeam extends MenuItem{
    public RemoveTeam(String name, MenuItem Back) {
        super(name, Back);
    }

    @Override
    public boolean update() {

        Iterator<Team> iterator = ApplicationRepository.getRepository().getTeamsIterator();

        ArrayList<Team> Teams = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        int size = 0;
        while (iterator.hasNext()) {
            Team team = iterator.next();
            Teams.add(team);
            size++;
        }

        boolean isValid = true;
        int choice;
        do {
            clearCli();

            if (!isValid) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + size);
            }

            System.out.println("\nChoose the team you desire to remove. WARNING!! this is irreversible:");

            if (size != 0) {
                for (int i = 0; i < size; i++) {
                    System.out.printf("%d] %s\n", i + 1, Teams.get(i).getName());
                }
            }
            else {
                System.out.println("There are no teams found....");
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
            System.out.printf("Team %s removed Successfully. Press Enter key to continue...\n",Teams.get(choice - 1).getName());
            ApplicationRepository.getRepository().removeTeam(Teams.get(choice - 1));

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

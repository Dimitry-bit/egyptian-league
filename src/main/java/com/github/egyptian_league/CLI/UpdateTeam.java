package com.github.egyptian_league.CLI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Team;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.*;

public class UpdateTeam extends MenuItem{
    static Team CurrentTeam;

    public UpdateTeam(String name, MenuItem Back) {
        super(name, Back);
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

            System.out.println("\nChoose the team you desire to update:");

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


        if (choice == 'x'){
            back();
            return true;
        }

        CurrentTeam = Teams.get(choice-1);

        currentMenuItem = currentMenuItem.getSubMenus().get(0);

        currentMenuItem.setName("Chosen Team is " + CurrentTeam.getName());



        return true;
    }
}

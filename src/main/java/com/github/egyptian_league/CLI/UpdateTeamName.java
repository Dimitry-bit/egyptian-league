package com.github.egyptian_league.CLI;

import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;
import static com.github.egyptian_league.CLI.UpdateTeam.CurrentTeam;

public class UpdateTeamName extends MenuItem {
    public UpdateTeamName(String name, MenuItem Back) {
        super(name, Back);
    }

    @Override
    public boolean update() {

        Scanner in = new Scanner(System.in);

        clearCli();
        System.out.println("Enter the new team name:");

        String input = in.nextLine();

        CurrentTeam.setName(input);

        back();

        currentMenuItem.setName("Chosen Team is " + CurrentTeam.getName());
        return true;
    }
}

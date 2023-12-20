package com.github.egyptian_league.CLI;

import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;
import static com.github.egyptian_league.CLI.ChoosePlayer.CurrentPlayer;
import static com.github.egyptian_league.CLI.UpdateTeam.CurrentTeam;

public class UpdatePlayerName extends MenuItem {
    public UpdatePlayerName(String name, UpdatePlayerInfo Back) {
        super(name,Back);
    }

    @Override
    public boolean update() {

        clearCli();

        System.out.println("Enter the new player name:");

        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        CurrentPlayer.setName(input);

        back();

        currentMenuItem.setName("Chosen Player is " + CurrentPlayer.getName());

        return true;
    }
}

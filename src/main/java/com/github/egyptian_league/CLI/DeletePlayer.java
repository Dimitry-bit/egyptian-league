package com.github.egyptian_league.CLI;

import com.github.egyptian_league.ApplicationRepository;

import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;
import static com.github.egyptian_league.CLI.ChoosePlayer.CurrentPlayer;

public class DeletePlayer extends MenuItem {
    public DeletePlayer(String name, MenuItem Back) {
        super(name, Back);
    }

    @Override
    public boolean update() {
        clearCli();

        Scanner in = new Scanner(System.in);
        System.out.printf("Player %s removed Successfully. Press Enter key to continue...\n",CurrentPlayer.getName());

        ApplicationRepository.getRepository().removePlayer(CurrentPlayer);

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
}

package com.github.egyptian_league.CLI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Referee;
import com.github.egyptian_league.Models.Stadium;

import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;

public class AddReferee extends MenuItem {
    public AddReferee(String name, MenuItem Back) {
        super(name, Back);
    }

    public boolean update() {
        clearCli();
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the Referee Name:");

        String RefereeName = in.nextLine();

        clearCli();

        System.out.printf("Referee Name : %s\n", RefereeName);
        char c;
        do {
            System.out.println("Save this Referee? [y to save, n to DISCARD!! all entered data]: ");
            c = in.nextLine().charAt(0);
        }while(c != 'y' && c != 'Y' && c != 'n' && c != 'N');

        if (c == 'y' || c == 'Y')
        {
            ApplicationRepository.getRepository().putReferee(new Referee(RefereeName));
            back();
        }
        else {
            back();
        }

        return true;
    }
}

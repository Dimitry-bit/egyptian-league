package com.github.egyptian_league.CLI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Player;
import com.github.egyptian_league.Models.Stadium;
import com.github.egyptian_league.Models.Team;

import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;

public class AddStadium extends MenuItem {



    public AddStadium(String name, MenuItem Back) {
        super(name,Back);
    }

    /*
    *  public Stadium(String name, String address) {
        id = UUID.randomUUID();
        this.name = name;
        this.address = address;
    }
    *
    * */

    @Override
    public boolean update() {
        clearCli();
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the Stadium Name:");

        String StadiumName = in.nextLine();

        clearCli();
        System.out.println("Enter the Stadium Address:");
        String StadiumAddress = in.nextLine();

        clearCli();

        System.out.printf("Stadium Name : %s\nStadium Address : %s\n", StadiumName,StadiumAddress);
        char c;
        do {
            System.out.println("Save this Stadium? [y to save, n to DISCARD!! all entered data]: ");
            c = in.nextLine().charAt(0);
        }while(c != 'y' && c != 'Y' && c != 'n' && c != 'N');

        if (c == 'y' || c == 'Y')
        {
            ApplicationRepository.getRepository().putStadium(new Stadium(StadiumName, StadiumAddress));
            back();
        }
        else {
            back();
        }

        return true;
    }
}

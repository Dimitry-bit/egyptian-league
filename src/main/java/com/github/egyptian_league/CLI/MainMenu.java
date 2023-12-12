package com.github.egyptian_league.CLI;

import static com.github.egyptian_league.CLI.CLI_Input.*;

public class MainMenu extends MenuItem {

    public MainMenu(String name, MenuItem Back) {
        super(name, Back);
        enumMenus = EnumMenus.mainMenu;
    }


    public boolean back()
    {
        clearCli();
        System.out.println("Exiting CLI");
        return false;
    }

}

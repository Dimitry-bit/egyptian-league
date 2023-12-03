package CLI;

import java.util.Scanner;

import static CLI.CLI_Input.*;

public class MainMenu extends MenuItem {

    public MainMenu(String name) {
        super(name);
    }

    public void update() {

        MenuItem.currentMenuItem = MenuItem.mainMenu;

        drawCli();


        switch (choice){
            case 1 : MenuItem.enumMenus = EnumMenus.teamMenu;
                     break;
            case 2 : MenuItem.enumMenus = EnumMenus.matchMenu;
                     break;
            case 3 : clearCli();
                     MenuItem.enumMenus = EnumMenus.backMain;
                     break;
        }

        return;

    }
}

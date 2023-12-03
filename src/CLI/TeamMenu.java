package CLI;

import java.util.Scanner;

import static CLI.CLI_Input.*;

public class TeamMenu extends MenuItem{

    public TeamMenu(String name){
        super(name);
    }

    public void update() {
        MenuItem.currentMenuItem = MenuItem.teamMenu;

        drawCli();


        switch (choice){
            case 1 : MenuItem.enumMenus = EnumMenus.addTeam;
                    break;
            case 2 : MenuItem.enumMenus = EnumMenus.updateTeam;
                    break;
            case 3 : MenuItem.enumMenus = EnumMenus.removeTeam;
                    break;
            case 4 : MenuItem.enumMenus = EnumMenus.backTeam;
                    break;
        }

        return;

    }

}

package CLI;

import static CLI.CLI_Input.*;

public class AddTeam extends MenuItem{


    public AddTeam(String name){
        super(name);
    }
    public void update() {
        MenuItem.currentMenuItem = MenuItem.addTeam;

        drawCli();

        switch (choice){
            case 1 : MenuItem.enumMenus = EnumMenus.enterPlayers;
                break;
            case 2 : MenuItem.enumMenus = EnumMenus.enterTeamName;
                break;
            case 3 : MenuItem.enumMenus = EnumMenus.backAddTeam;
                break;
        }

        return;
    }

}

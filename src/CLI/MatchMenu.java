package CLI;

import static CLI.CLI_Input.*;

public class MatchMenu extends MenuItem{

    public MatchMenu(String name){
        super(name);
    }

    public void update() {
        MenuItem.currentMenuItem = MenuItem.matchMenu;

        drawCli();


        switch (choice){
            case 1 : MenuItem.enumMenus = EnumMenus.addMatch;
                break;
            case 2 : MenuItem.enumMenus = EnumMenus.updateMatch;
                break;
            case 3 : MenuItem.enumMenus = EnumMenus.removeMatch;
                break;
            case 4 : MenuItem.enumMenus = EnumMenus.backMatch;
                break;
        }

        return;

    }
}

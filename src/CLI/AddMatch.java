package CLI;

import static CLI.CLI_Input.*;

public class AddMatch extends MenuItem{


public AddMatch(String name){
    super(name);
}

    public void update() {
        MenuItem.currentMenuItem = MenuItem.addMatch;

        drawCli();

        switch (choice){
            case 1 : MenuItem.enumMenus = EnumMenus.chooseTeams;
                break;
            case 2 : MenuItem.enumMenus = EnumMenus.chooseDate;
                break;
            case 3 : MenuItem.enumMenus = EnumMenus.backAddMatch;
                break;
        }

        return;
    }

}

package CLI;

import static CLI.CLI_Input.clearCli;
import static CLI.CLI_Input.drawCli;

public class BackTeam extends MenuItem{
    public BackTeam(String name) {
        super(name);
    }

    public void update() {
        MenuItem.currentMenuItem = MenuItem.backTeam;

        MenuItem.enumMenus = EnumMenus.mainMenu;

        return;
    }
}

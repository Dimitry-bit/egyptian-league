package CLI;

import static CLI.CLI_Input.clearCli;
import static CLI.CLI_Input.drawCli;

public class RemoveTeam extends MenuItem{
    public RemoveTeam(String name) {
        super(name);
    }

    public void update() {
        MenuItem.currentMenuItem = MenuItem.removeTeam;

        return;
    }
}

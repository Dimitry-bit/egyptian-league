package CLI;

import static CLI.CLI_Input.clearCli;
import static CLI.CLI_Input.drawCli;

public class UpdateTeam extends MenuItem{
    UpdateTeam(String name){
        super(name);
    }

    public void update() {
        MenuItem.currentMenuItem = MenuItem.updateTeam;

        drawCli();
    }
}

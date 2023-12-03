package CLI;

import static CLI.CLI_Input.*;

public class BackMain extends MenuItem{
    public BackMain(String name) {
        super(name);
    }

    public void update() {

        MenuItem.currentMenuItem = backMain;
        clearCli();

        System.out.println("Exiting CLI");
        return;
    }
}

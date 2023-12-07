package CLI;

import java.util.Scanner;

import static CLI.CLI_Input.*;
import static CLI.EnumMenus.*;

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

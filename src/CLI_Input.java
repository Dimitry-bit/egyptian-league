
import java.util.Scanner;

public class CLI_Input{

    static MenuItem mainMenu;
    static MenuItem teamMenu;
    static MenuItem addTeam;
    static MenuItem matchMenu;
    static MenuItem addMatch;
    public static void start(){
        setMenu();
        navMenu();
    }
    public static void setMenu(){
        //  Main Menu
        mainMenu = new MenuItem("Main Menu");

        mainMenu.addSubMenu(new MenuItem("Teams"));
        mainMenu.addSubMenu(new MenuItem("Matches"));

        // Team Menu with Sub menus
        teamMenu = mainMenu.getSubMenus().get(0);

        teamMenu.addSubMenu(new MenuItem("Add Team"));
        teamMenu.addSubMenu(new MenuItem("Update Team"));
        teamMenu.addSubMenu(new MenuItem("Remove Team"));
        teamMenu.addSubMenu(new MenuItem("Back"));

        addTeam = teamMenu.getSubMenus().get(0);

        addTeam.addSubMenu(new MenuItem("Enter Team Name"));
        addTeam.addSubMenu(new MenuItem("Enter 11 Players"));

        // Match Menu with Sub menus
        matchMenu = mainMenu.getSubMenus().get(1);

        matchMenu.addSubMenu(new MenuItem("Add Match"));
        matchMenu.addSubMenu(new MenuItem("Update Match"));
        matchMenu.addSubMenu(new MenuItem("Remove Match"));
        matchMenu.addSubMenu(new MenuItem("Back"));

        addMatch = matchMenu.getSubMenus().get(0);
        addMatch.addSubMenu(new MenuItem("Choose 2 Teams"));
        addMatch.addSubMenu(new MenuItem("Date"));

    }

    public static void navMenu(){
        MenuItem currentMenuItem = mainMenu;


        boolean isValid = true;

        while (true) {

            clearCli();

            if(!isValid)
            {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + currentMenuItem.getSubMenus().size());
                isValid = true;
            }

            System.out.println("\nCurrent Menu:");
            MenuItem.printMenu(currentMenuItem);

            //moves cursor to the end of terminal
            System.out.print("\033[9999H");

            System.out.println("Enter your choice: ");

            Scanner in = new Scanner(System.in);
            int choice = Integer.parseInt(in.nextLine());

            if (choice < 1 || choice > currentMenuItem.getSubMenus().size()) {
                isValid = false;
                continue;
            }

            MenuItem selectedMenuItem = currentMenuItem.getSubMenus().get(choice - 1);

            if (selectedMenuItem.hasSubMenus()) {
                currentMenuItem = selectedMenuItem;
                continue;
            }

            if (selectedMenuItem.getName().equals("Back")) {
                currentMenuItem = mainMenu;
                continue;
            }


            clearCli();
            System.out.println("Implementation of " + selectedMenuItem.getName());
            break;




        }
    }

    public static void clearCli(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
               new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("An error occurred while clearing the CLI: " + e.getMessage());
        }
    }


}



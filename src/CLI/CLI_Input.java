package CLI;

import java.util.Scanner;


public class CLI_Input{

    static boolean isValid = true;

    static int choice;

    public static void start(){
        InitMenu();
        navMenu();

    }
    public static void InitMenu(){

        //  Main Menu
        MenuItem.mainMenu = new MainMenu("Main Menu");

        MenuItem.teamMenu = new TeamMenu("Teams");
        MenuItem.mainMenu.addSubMenu(MenuItem.teamMenu);

        MenuItem.matchMenu = new MatchMenu("Matches");
        MenuItem.mainMenu.addSubMenu(MenuItem.matchMenu);

        MenuItem.backMain = new BackMain("Exit");
        MenuItem.mainMenu.addSubMenu(MenuItem.backMain);


        // Team Menu with Sub menus
        MenuItem.addTeam = new AddTeam("Add Team");
        MenuItem.teamMenu.addSubMenu(MenuItem.addTeam);

        MenuItem.updateTeam = new UpdateTeam("Update Team");
        MenuItem.teamMenu.addSubMenu(MenuItem.updateTeam);

        MenuItem.removeTeam = new RemoveTeam("Remove Team");
        MenuItem.teamMenu.addSubMenu(MenuItem.removeTeam);

        MenuItem.backTeam = new BackTeam("Back");
        MenuItem.teamMenu.addSubMenu(MenuItem.backTeam);

        //Add team with submenus
        MenuItem.enterTeamName = new EnterTeamName("Enter Team Name");
        MenuItem.addTeam.addSubMenu(MenuItem.enterTeamName);

        MenuItem.enterPlayers = new EnterPlayers("Enter 11 Players");
        MenuItem.addTeam.addSubMenu(MenuItem.enterPlayers);

        MenuItem.backAddTeam = new BackAddTeam("Back");
        MenuItem.addTeam.addSubMenu(MenuItem.backAddTeam);


        // Match Menu with Sub menus

        MenuItem.addMatch = new AddMatch("Add Match");
        MenuItem.matchMenu.addSubMenu(MenuItem.addMatch);

        MenuItem.updateMatch = new UpdateMatch("Update Match");
        MenuItem.matchMenu.addSubMenu(MenuItem.updateMatch);

        MenuItem.removeMatch = new RemoveMatch("Remove Match");
        MenuItem.matchMenu.addSubMenu(MenuItem.removeMatch);

        MenuItem.backMatch = new BackMatch("Back");
        MenuItem.matchMenu.addSubMenu(MenuItem.backMatch);

        // Add match with submenus

        MenuItem.chooseTeams = new ChooseTeams("Choose 2 Teams");
        MenuItem.addMatch.addSubMenu(MenuItem.chooseTeams);

        MenuItem.chooseDate = new ChooseDate("Date");
        MenuItem.addMatch.addSubMenu(MenuItem.chooseDate);

        MenuItem.backAddMatch = new BackAddMatch("Back");
        MenuItem.addMatch.addSubMenu(MenuItem.backAddMatch);




    }

    public static void navMenu(){

        while(true) {

            switch (MenuItem.enumMenus) {
                case mainMenu:
                    MenuItem.mainMenu.update();
                    break;

                case teamMenu:
                    MenuItem.teamMenu.update();
                    break;

                case backTeam:
                    MenuItem.backTeam.update();
                    break;

                case addTeam:
                    MenuItem.addTeam.update();
                    break;

                case backAddTeam:
                    MenuItem.backAddTeam.update();
                    break;

                case matchMenu:
                    MenuItem.matchMenu.update();
                    break;

                case backMatch:
                    MenuItem.backMatch.update();
                    break;

                case addMatch:
                    MenuItem.addMatch.update();
                    break;

                case backAddMatch:
                    MenuItem.backAddMatch.update();
                    break;

                case backMain:
                    MenuItem.backMain.update();
                    return;

                default:
                    clearCli();
                    System.out.println("no implementation");
                    return;
            }
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

    public static void drawCli(){

        while (true){

            clearCli();

            if (!isValid) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + MenuItem.currentMenuItem.getSubMenus().size());
                isValid = true;
            }

            System.out.println("\nCurrent Menu: " + MenuItem.currentMenuItem.getName());
            MenuItem.printMenu(MenuItem.currentMenuItem);

            String eofTerminal = "\033[9999H";
            System.out.print(eofTerminal);

            System.out.println("Enter your choice: ");

            Scanner in = new Scanner(System.in);
            choice = Integer.parseInt(in.nextLine());


            if(choice < 1 || choice > MenuItem.currentMenuItem.getSubMenus().size()) {
                isValid = false;
                continue;
            }

            return;
        }
    }

}





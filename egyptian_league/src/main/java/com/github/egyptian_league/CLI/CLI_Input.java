package com.github.egyptian_league.CLI;

import java.util.Scanner;


public class CLI_Input{

    public static void start(){
        InitMenu();
        navMenu();

    }
    public static void InitMenu(){

        //  com.github.egyptian_league.Main Menu with its Sub menus

        MenuItem.currentMenuItem = new MainMenu("com.github.egyptian_league.Main Menu", null);

        MenuItem teamMenu = new TeamMenu("Teams", MenuItem.currentMenuItem);

        MenuItem matchMenu = new MatchMenu("Matches", MenuItem.currentMenuItem);

        MenuItem.currentMenuItem.addSubMenu(teamMenu);
        MenuItem.currentMenuItem.addSubMenu(matchMenu);



        // com.github.egyptian_league.Team Menu with Sub menus

        MenuItem addTeam = new AddTeam("Add com.github.egyptian_league.Team",teamMenu);

        teamMenu.addSubMenu(addTeam);
        teamMenu.addSubMenu( new UpdateTeam("Update com.github.egyptian_league.Team", teamMenu) );
        teamMenu.addSubMenu( new RemoveTeam("Remove com.github.egyptian_league.Team", teamMenu) );

        //Add team with submenus

        addTeam.addSubMenu(new EnterTeamName("Enter com.github.egyptian_league.Team Name",addTeam));
        addTeam.addSubMenu(new EnterPlayers("Enter 11 Players",addTeam));



        // com.github.egyptian_league.Match Menu with Sub menus

        MenuItem addMatch = new AddMatch("Add com.github.egyptian_league.Match", matchMenu);

        matchMenu.addSubMenu(addMatch);
        matchMenu.addSubMenu(new UpdateMatch("Update com.github.egyptian_league.Match",matchMenu));
        matchMenu.addSubMenu(new RemoveMatch("Remove com.github.egyptian_league.Match",matchMenu));

        // Add match with submenus

        addMatch.addSubMenu(new ChooseTeams("Choose 2 Teams",addMatch));
        addMatch.addSubMenu(new ChooseDate("com.github.egyptian_league.Date",addMatch));

    }

    public static void navMenu(){

        //weird af?
        while(MenuItem.currentMenuItem.update()) { }

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

    public static void drawCli(boolean isValid){

    clearCli();

    if (!isValid) {
        System.out.println("\nInvalid choice. Please enter 'x' or a number between 1 and " + MenuItem.currentMenuItem.getSubMenus().size());
    }

    System.out.println("\nCurrent Menu: " + MenuItem.currentMenuItem.getName());
    MenuItem.printMenu(MenuItem.currentMenuItem);

    String eofTerminal = "\033[9999H";
    System.out.print(eofTerminal);

    System.out.println("Enter your choice: ");
    }

    public static int getInput() {

        Scanner in = new Scanner(System.in);

        char inputChar = in.nextLine().charAt(0);

        int choice;

        if (Character.isDigit(inputChar)) {
            choice = Character.getNumericValue(inputChar);
        } else {
            choice = inputChar;
        }


        if ((choice < 1 || choice > MenuItem.currentMenuItem.getSubMenus().size()) && choice != 'x') {
            return 0;
        }
        else{
            return choice;
        }
    }




}





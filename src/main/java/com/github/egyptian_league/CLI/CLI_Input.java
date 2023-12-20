package com.github.egyptian_league.CLI;

import java.util.Scanner;


public class CLI_Input{

    public static void start(){
        InitMenu();
        navMenu();

    }
    public static void InitMenu(){

        //  com.github.egyptian_league.Main Menu with its Sub menus

        MenuItem.currentMenuItem = new MainMenu("Main Menu", null);

        MenuItem teamMenu = new TeamMenu("Teams", MenuItem.currentMenuItem);

        MenuItem matchMenu = new MatchMenu("Matches", MenuItem.currentMenuItem);

        MenuItem stadiumMenu = new StadiumMenu("Stadiums", MenuItem.currentMenuItem);

        MenuItem refereeMenu = new RefereeMenu("Referees", MenuItem.currentMenuItem);

        MenuItem.currentMenuItem.addSubMenu(teamMenu);
        MenuItem.currentMenuItem.addSubMenu(matchMenu);
        MenuItem.currentMenuItem.addSubMenu(stadiumMenu);
        MenuItem.currentMenuItem.addSubMenu(refereeMenu);



        // com.github.egyptian_league.Team Menu with Sub menus

        MenuItem updateTeam = new UpdateTeam("Update Team",teamMenu);

        teamMenu.addSubMenu(new AddTeam("Add Team",teamMenu));
        teamMenu.addSubMenu(updateTeam);
        teamMenu.addSubMenu( new RemoveTeam("Remove Team", teamMenu) );


        //Update team with submenus

        ChosenTeam chosenTeam = new ChosenTeam("Chosen Team", updateTeam);
        updateTeam.addSubMenu(chosenTeam);

        //Chosen team with submenus

        chosenTeam.addSubMenu(new UpdateTeamName("Edit Team's Name",chosenTeam));

        ChoosePlayer choosePlayer = new ChoosePlayer("Player Info", chosenTeam);
        chosenTeam.addSubMenu(choosePlayer);

        //Choose player with submenus

        UpdatePlayerInfo updatePlayerInfo = new UpdatePlayerInfo("Chosen Player",choosePlayer);
        choosePlayer.addSubMenu(updatePlayerInfo);

        //Update player info with submenus

        updatePlayerInfo.addSubMenu(new UpdatePlayerName("Edit Player's Name", updatePlayerInfo));
        updatePlayerInfo.addSubMenu(new UpdatePlayerBirthdate("Edit Player's Birthdate", updatePlayerInfo));
        updatePlayerInfo.addSubMenu(new UpdatePlayerPosition("Edit Player's Position",updatePlayerInfo));
        updatePlayerInfo.addSubMenu(new UpdatePlayerTshirt("Edit Player's Tshirt No.", updatePlayerInfo));
        updatePlayerInfo.addSubMenu(new DeletePlayer("Remove This Player \"WARNING: This is irreversible\"", updatePlayerInfo));

        // com.github.egyptian_league.Match Menu with Sub menus

        MenuItem addMatch = new AddMatch("Add Match", matchMenu);

        matchMenu.addSubMenu(addMatch);
        matchMenu.addSubMenu(new UpdateMatch("Update Match",matchMenu));
        matchMenu.addSubMenu(new RemoveMatch("Remove Match",matchMenu));

        //Stadiums with submenus

        stadiumMenu.addSubMenu(new AddStadium ("Add Stadium", stadiumMenu));
        stadiumMenu.addSubMenu(new RemoveStadium("Remove Stadium",stadiumMenu));

        //Referees with submenus
        refereeMenu.addSubMenu(new AddReferee ("Add Referee", refereeMenu));
        refereeMenu.addSubMenu(new RemoveReferee("Remove Referee",refereeMenu));

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

        int choice;
        String input = in.nextLine();

        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            choice = (int) input.charAt(0);
        }

        if ((choice < 1 || choice > MenuItem.currentMenuItem.getSubMenus().size()) && choice != 'x') {
            return 0;
        }
        else{
            return choice;
        }
    }




}





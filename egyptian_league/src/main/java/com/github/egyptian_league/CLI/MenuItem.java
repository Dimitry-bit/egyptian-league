package com.github.egyptian_league.CLI;

import java.util.ArrayList;
import java.util.List;

import static com.github.egyptian_league.CLI.CLI_Input.*;

abstract class MenuItem {

    static MenuItem currentMenuItem;
    MenuItem Back;

    public EnumMenus enumMenus;
    private String name;
    private List<MenuItem> subMenus;

    public MenuItem(String name, MenuItem Back) {
        this.name = name;
        this.subMenus = new ArrayList<>();
        this.Back = Back;

    }

    public static void printMenu(MenuItem menuItem) {
        for (int i = 0; i < menuItem.getSubMenus().size(); i++) {

            MenuItem subMenuItem = menuItem.getSubMenus().get(i);
            System.out.println((i + 1) + "] " + subMenuItem.getName());

        }

        if (menuItem.enumMenus == EnumMenus.mainMenu)
        {
            System.out.println("x] Exit");
        }
        else if (menuItem.hasBack()){
            System.out.println("x] Back");
        }


    }
    public String getName() {
        return name;
    }



    public List<MenuItem> getSubMenus() {
        return subMenus;
    }

    public boolean hasSubMenus() {
        return !subMenus.isEmpty();
    }

    //determining main menu could be better maybe?
    public boolean hasBack() {
        return Back != null;
    }

    public void addSubMenu(MenuItem subMenu) {
        subMenus.add(subMenu);
    }


    //false indicates no further update i.e. exit the program
    public boolean update() {

    //temporary condition will be removed later
    if (!currentMenuItem.hasSubMenus())
    {
        clearCli();
        System.out.println("implementation of \"" + currentMenuItem.name + "\" here");
        return false;
    }

    boolean isValid = true;
    int choice;
    do {

    drawCli(isValid);
    choice = getInput();

    if (choice == 0){
        isValid = false;
    }

    }while(!isValid);


    if (choice == 'x') {
        return back(); //goes back, and the boolean indicates whether the program updates or not
    }
    else {
        currentMenuItem = MenuItem.currentMenuItem.getSubMenus().get(choice - 1);
    }
    return true;

    }

    public boolean back() {
        currentMenuItem = Back;
        return true;
    }



}

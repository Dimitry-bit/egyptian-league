package CLI;

import java.util.ArrayList;
import java.util.List;

abstract class MenuItem {

    static MenuItem mainMenu, backMain;
    static MenuItem teamMenu, addTeam, updateTeam, removeTeam, backTeam,
                              enterPlayers, enterTeamName, backAddTeam;
    static MenuItem matchMenu, addMatch, updateMatch, removeMatch, backMatch,
                               chooseTeams, chooseDate, backAddMatch;

    static MenuItem currentMenuItem;

    public static EnumMenus enumMenus = EnumMenus.mainMenu;
    private String name;
    private List<MenuItem> subMenus;

    public MenuItem(String name) {
        this.name = name;
        this.subMenus = new ArrayList<>();

    }

    public static void printMenu(MenuItem menuItem) {
        for (int i = 0; i < menuItem.getSubMenus().size(); i++) {
            MenuItem subMenuItem = menuItem.getSubMenus().get(i);
            System.out.println((i + 1) + "] " + subMenuItem.getName());
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

    public void addSubMenu(MenuItem subMenu) {
        subMenus.add(subMenu);
    }

    public void update() {
    }
}

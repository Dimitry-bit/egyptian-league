package CLI;

public class BackMatch extends MenuItem{
    public BackMatch(String name) {
        super(name);
    }

    public void update() {
        MenuItem.currentMenuItem = MenuItem.matchMenu;

        MenuItem.enumMenus = EnumMenus.mainMenu;


        return;
    }
}

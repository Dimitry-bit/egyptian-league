package CLI;

public class BackAddMatch extends MenuItem{
    public BackAddMatch(String name) {
        super(name);
    }

    public void update() {
        MenuItem.currentMenuItem = MenuItem.backAddMatch;

        MenuItem.enumMenus = EnumMenus.matchMenu;

        return;
    }
}

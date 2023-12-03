package CLI;

public class BackAddTeam extends MenuItem{
    public BackAddTeam(String name) {
        super(name);
    }

    public void update() {
        MenuItem.currentMenuItem = MenuItem.backAddTeam;

        MenuItem.enumMenus = EnumMenus.teamMenu;

        return;
    }
}

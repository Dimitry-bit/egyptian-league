import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Team {
    private SimpleStringProperty teamName;
    private SimpleIntegerProperty ID;

    public Team(String teamName, int ID) {
        this.teamName = new SimpleStringProperty(teamName);
        this.ID = new SimpleIntegerProperty(ID);
    }

    public String getTeamName() {
        return teamName.get();
    }

    public SimpleStringProperty teamNameProperty() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }

    public int getID() {
        return ID.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }
}

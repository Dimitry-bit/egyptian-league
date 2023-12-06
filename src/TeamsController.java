import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeamsController implements Initializable {
    // configure the table
    @FXML
    private TableView<Team> tableView;
    @FXML private TableColumn<Team,String> teamNameColumn;
    @FXML private TableColumn<Team, SimpleIntegerProperty> teamIDColumn;

    public void changeScreenToTeams(ActionEvent event) throws IOException
    {
        Parent homePageLoader = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene homePageScene = new Scene(homePageLoader);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homePageScene);
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<Team,String>("teamName"));
        teamIDColumn.setCellValueFactory(new PropertyValueFactory<Team,SimpleIntegerProperty>("teamID"));

        tableView.setItems(getTeams());

    }
    public ObservableList<Team> getTeams()
    {
        ObservableList<Team> teams = FXCollections.observableArrayList();
        teams.add(new Team("Zamalek",1));
        teams.add(new Team("El Ahly",2));
        teams.add(new Team("Ismaily",3));

        return teams;

    }
}

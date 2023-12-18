package com.github.egyptian_league;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeamsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<InsertTeams, Integer> TeamId;

    @FXML
    private TableColumn<InsertTeams, String> TeamName;

    @FXML
    private TableView<InsertTeams> TeamsTable;

    @FXML
    private TextField textTeamName;

    @FXML
    private TextField textTeamId;

    ObservableList<InsertTeams> initialData(){
        InsertTeams team1 = new InsertTeams("Zamalek",1000);
        InsertTeams team2 = new InsertTeams("Zed Fc",2000);
        return FXCollections.observableArrayList(team1,team2);

    }

    @FXML
    public void btnInsert(ActionEvent event){
        InsertTeams newData = new InsertTeams(textTeamName.getText(),textTeamId.getText());
        TeamsTable.getItems().add(newData);
        textTeamName.clear();

    }
    public void SwitchToHomePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TeamName.setCellValueFactory(new PropertyValueFactory<InsertTeams,String>("TeamName"));
        TeamId.setCellValueFactory(new PropertyValueFactory<InsertTeams,Integer>("TeamId"));

        TeamsTable.setItems(initialData());
    }
}

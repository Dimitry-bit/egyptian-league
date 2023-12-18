package com.github.egyptian_league;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.UUID;

public class TeamsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<TeamPojo, String> TeamName;

    @FXML
    private TableColumn<TeamPojo, String> TeamCaptain;

    @FXML
    private TableColumn<TeamPojo, Integer> TeamTotalScore;

    @FXML
    private TableView<TeamPojo> TeamsTable;

    @FXML
    private TextField textTeamName;

    @FXML
    private TextField textTeamCaptain;

    public void seedTable() {
        Iterator<Team> teamsIterator = ApplicationRepository.getRepository().getTeamsIterator();
        while (teamsIterator.hasNext()) {
            TeamPojo pojo = new TeamPojo(teamsIterator.next());
            TeamsTable.getItems().add(pojo);
        }
    }

    @FXML
    public void btnInsert(ActionEvent event) {
        try {
            if (!ApplicationRepository.getRepository().containsPlayerName(textTeamCaptain.getText())) {
                // TODO: Show error
                return;
            }

            Player captain = ApplicationRepository.getRepository().getPlayersByName(textTeamCaptain.getText())[0];

            Team team = new Team(textTeamName.getText(), captain.getId());
            TeamPojo teamPojo = new TeamPojo(team);

            // TODO: Add to repository
            ApplicationRepository.getRepository().putTeam(team);

            TeamsTable.getItems().add(teamPojo);

            clearInput();
        } catch (Exception e) {
            // TODO: handle exception
            System.err.printf("Invalid data, %s", e.getMessage());
            e.printStackTrace();
        }
    }

    public void SwitchToHomePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TeamName.setCellValueFactory(new PropertyValueFactory<>("TeamName"));
        TeamCaptain.setCellValueFactory(new PropertyValueFactory<>("TeamCaptain"));
        TeamTotalScore.setCellValueFactory(new PropertyValueFactory<>("TotalScore"));

        TeamName.setCellFactory(TextFieldTableCell.forTableColumn());
        TeamName.setOnEditCommit(event -> {
           event.getRowValue().getTeam().setName(event.getNewValue());;
        });
        TeamCaptain.setCellFactory(TextFieldTableCell.forTableColumn());
        
        seedTable();
    }

    private void clearInput() {
        textTeamName.clear();
        textTeamCaptain.clear();
    }
}

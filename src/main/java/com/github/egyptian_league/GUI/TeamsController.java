package com.github.egyptian_league.GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Player;
import com.github.egyptian_league.Models.Position;
import com.github.egyptian_league.Models.Team;
import com.github.egyptian_league.POJOs.PlayerPojo;
import com.github.egyptian_league.POJOs.TeamPojo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class TeamsController implements Initializable {

    @FXML
    private TableView<TeamPojo> TeamsTable;
    @FXML
    private TableColumn<TeamPojo, String> TeamName;
    @FXML
    private TableColumn<TeamPojo, String> TeamCaptain;
    @FXML
    private TableColumn<TeamPojo, Integer> TeamTotalScore;

    @FXML
    private TableView<PlayerPojo> PlayersTable;
    @FXML
    private TableColumn<PlayerPojo, String> PlayerName;
    @FXML
    private TableColumn<PlayerPojo, LocalDate> PlayerBirthdate;
    @FXML
    private TableColumn<PlayerPojo, Position> PlayerPosition;
    @FXML
    private TableColumn<PlayerPojo, Integer> PlayerShirtNumber;
    @FXML
    private TableColumn<PlayerPojo, Integer> PlayerAge;
    @FXML
    private TableColumn<PlayerPojo, Integer> PlayerRank;

    @FXML
    private TextField textTeamName;
    @FXML
    private TextField textTeamCaptain;

    @FXML
    public void btnInsert(ActionEvent event) {
        try {
            if (textTeamName.getText().isBlank() || textTeamCaptain.getText().isBlank()) {
                return;
            }

            if (!ApplicationRepository.getRepository().containsPlayerName(textTeamCaptain.getText())) {
                showError(event);
                return;
            }

            Player captain = ApplicationRepository.getRepository().getPlayersByName(textTeamCaptain.getText())[0];
            Team team = new Team(textTeamName.getText(), captain.Id);
            TeamPojo teamPojo = new TeamPojo(team);

            ApplicationRepository.getRepository().putTeam(team);
            TeamsTable.getItems().add(teamPojo);

            clearInput();
        } catch (Exception e) {
            // TODO: handle exception
            System.err.printf("Invalid data, %s", e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTeamsTableView();
        initializePlayersTableView();

        seedTeamsTableView();
    }

    private void initializeTeamsTableView() {
        TeamName.setCellValueFactory(new PropertyValueFactory<>("TeamName"));
        TeamCaptain.setCellValueFactory(new PropertyValueFactory<>("TeamCaptain"));
        TeamTotalScore.setCellValueFactory(new PropertyValueFactory<>("TotalScore"));

        TeamName.setCellFactory(TextFieldTableCell.forTableColumn());
        TeamName.setOnEditCommit(event -> {
            event.getRowValue().getTeam().setName(event.getNewValue());
        });

        TeamCaptain.setCellFactory(TextFieldTableCell.forTableColumn());
        TeamCaptain.setOnEditCommit(event -> {
            event.getRowValue().getTeam().getCaptain().setName(event.getNewValue());
        });
    }

    private void initializePlayersTableView() {
        TeamsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            PlayersTable.getItems().clear();
            if (newSelection == null) {
                return;
            }

            List<Player> players = newSelection.getTeam().getPlayers();
            for (Player p : players) {
                PlayerPojo pojo = new PlayerPojo(p);
                PlayersTable.getItems().add(pojo);
            }
        });

        PlayerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PlayerAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        PlayerBirthdate.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        PlayerPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        PlayerRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        PlayerShirtNumber.setCellValueFactory(new PropertyValueFactory<>("shirtNumber"));
    }

    private void showError(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert!");
        alert.setContentText("Invalid Data");
        alert.showAndWait();
    }

    private void clearInput() {
        textTeamName.clear();
        textTeamCaptain.clear();
    }

    private void seedTeamsTableView() {
        Iterator<Team> teamsIterator = ApplicationRepository.getRepository().getTeamsIterator();
        while (teamsIterator.hasNext()) {
            TeamPojo pojo = new TeamPojo(teamsIterator.next());
            TeamsTable.getItems().add(pojo);
        }
    }
}

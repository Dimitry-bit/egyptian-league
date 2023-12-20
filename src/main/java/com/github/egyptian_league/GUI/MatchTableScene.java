package com.github.egyptian_league.GUI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.*;
import com.github.egyptian_league.POJOs.MatchPojo;
import com.github.egyptian_league.POJOs.ScorersPojo;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.Map.Entry;

public class MatchTableScene extends TableScene<MatchPojo> {

    @FXML
    TableView<ScorersPojo> goalsTableView;
    @FXML
    HBox goalsInputHBox;

    public void addRow() {
        String homeTeamText = textFields.get("Home Team").getText();
        String awayTeamText = textFields.get("Away Team").getText();
        LocalDate date = datePickers.get("Date").getValue();
        String timeStr = textFields.get("Time").getText();

        try {
            String stadiumText = (String) comboBoxes.get("Stadium").getValue();
            String refereeText = (String) comboBoxes.get("Referee").getValue();

            boolean isMissingData = (stadiumText == null)
                    || (refereeText == null)
                    || homeTeamText.isBlank()
                    || awayTeamText.isBlank()
                    || timeStr.isBlank()
                    || (date == null);

            if (isMissingData) {
                return;
            }

            LocalTime time = LocalTime.parse(timeStr);

            if (!ApplicationRepository.getRepository().containsTeamName(homeTeamText)
                    || !ApplicationRepository.getRepository().containsTeamName(awayTeamText)) {
                GuiUtils.showAlert("Input Error", "Home team or away team does not exist.", AlertType.ERROR);
                return;
            }

            if (!ApplicationRepository.getRepository().containsStadiumName(stadiumText)) {
                GuiUtils.showAlert("Input Error", "Stadium does not exit", AlertType.ERROR);
                return;
            }

            if (!ApplicationRepository.getRepository().containsRefereeName(refereeText)) {
                GuiUtils.showAlert("Input Error", "Referee does not exit", AlertType.ERROR);
                return;
            }

            LocalDateTime dateTime = LocalDateTime.of(date, time);
            Team homeTeam = ApplicationRepository.getRepository().getTeamsByName(homeTeamText)[0];
            Team awayTeam = ApplicationRepository.getRepository().getTeamsByName(awayTeamText)[0];
            Stadium stadium = ApplicationRepository.getRepository().getStadiumsByName(stadiumText)[0];
            Referee referee = ApplicationRepository.getRepository().getRefereesByName(refereeText)[0];

            Match match = new Match(homeTeam.Id, awayTeam.Id, stadium.Id, referee.Id, dateTime);

            ApplicationRepository.getRepository().putMatch(match);

            tableView.getItems().add(new MatchPojo(match));

            clearInput();
        } catch (DateTimeParseException e) {
            GuiUtils.showAlert("Input Error", "Invalid time.", AlertType.ERROR);
        } catch (Exception e) {
            GuiUtils.showAlert("Error", e.getMessage(), AlertType.ERROR);
        }
    }

    public void onScorerInsertion() {
        try {
            String playerName = (String) comboBoxes.get("Player").getValue();
            String goalsText = textFields.get("Goals").getText();
            MatchPojo matchPojo = tableView.getSelectionModel().getSelectedItem();

            if ((playerName == null) || goalsText.isBlank() || (matchPojo == null)) {
                return;
            }

            int goals = Integer.parseInt(goalsText);
            if (goals <= 0) {
                GuiUtils.showAlert("Input Error", "Invalid goals.", AlertType.ERROR);
                return;
            }

            Player player = ApplicationRepository.getRepository().getPlayersByName(playerName)[0];
            matchPojo.getMatch().putGoals(player.Id, goals);

            goalsTableView.getItems().add(new ScorersPojo(player, matchPojo.getMatch()));
            goalsTableView.refresh();
            tableView.refresh();

            clearGoalInput();
        } catch (NumberFormatException e) {
            GuiUtils.showAlert("Input Error", "Goals is not a number", AlertType.ERROR);
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e.getMessage());
            GuiUtils.showAlert("Error", e.getMessage(), AlertType.ERROR);
        }
    }

    public void onMatchSelection(MatchPojo selectedMatch) {
        goalsTableView.getItems().clear();
        ComboBox<String> playersComboBox = (ComboBox<String>) comboBoxes.get("Player");
        playersComboBox.getItems().clear();

        if (selectedMatch == null) {
            return;
        }

        ArrayList<Player> players = selectedMatch.getMatch().getHomeTeam().getPlayers();
        players.addAll(selectedMatch.getMatch().getAwayTeam().getPlayers());
        String[] playerNames = new String[players.size()];

        for (int i = 0; i < players.size(); ++i) {
            playerNames[i] = players.get(i).getName();
        }

        playersComboBox.getItems().addAll(playerNames);

        for (Entry<UUID, Integer> e : selectedMatch.getMatch().getScorers().entrySet()) {
            Player player = ApplicationRepository.getRepository().getPlayerByUUID(e.getKey());
            goalsTableView.getItems().add(new ScorersPojo(player, selectedMatch.getMatch()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTextField("Home Team", 100, 30, inputHBox, null);
        createTextField("Away Team", 100, 30, inputHBox, null);

        Iterator<Stadium> stadiumsIterator = ApplicationRepository.getRepository().getStadiumsIterator();
        ArrayList<String> stadiumNames = new ArrayList<>();
        while (stadiumsIterator.hasNext()) {
            stadiumNames.add(stadiumsIterator.next().getName());
        }
        createComboBox("Stadium", stadiumNames.toArray(String[]::new), 100, 30, inputHBox, null);

        Iterator<Referee> refereeIterator = ApplicationRepository.getRepository().getRefereesIterator();
        ArrayList<String> refereeNames = new ArrayList<>();
        while (refereeIterator.hasNext()) {
            refereeNames.add(refereeIterator.next().getName());
        }
        createComboBox("Referee", refereeNames.toArray(String[]::new), 100, 30, inputHBox, null);

        createDatePicker("Date", 100, 30, inputHBox, null);
        createTextField("Time", 100, 30, inputHBox, null);

        createButton("Insert", 100, 30, inputHBox, null, event -> {
            addRow();
        });

        createButton("Delete", 100, 30, inputHBox, null, event -> {
            MatchPojo pojo = tableView.getSelectionModel().getSelectedItem();

            if (pojo == null) {
                return;
            }

            ApplicationRepository.getRepository().removeMatch(pojo.getMatch());

            tableView.getItems().remove(pojo);
            tableView.getSelectionModel().clearSelection();
            tableView.refresh();
        });

        createTableColumn("HomeTeam", String.class, tableView);
        createTableColumn("AwayTeam", String.class, tableView);
        createTableColumn("Stadium", String.class, tableView);
        createTableColumn("Referee", String.class, tableView);
        createTableColumn("Date", LocalDateTime.class, tableView);
        createTableColumn("Winner", String.class, tableView);

        createTableColumn("TeamName", String.class, goalsTableView);
        createTableColumn("PlayerName", String.class, goalsTableView);
        createTableColumn("PlayerScore", Integer.class, goalsTableView);

        createComboBox("Player", new String[0], 100, 30, goalsInputHBox, null);
        createTextField("Goals", 100, 30, goalsInputHBox, null);

        createButton("Insert", 100, 30, goalsInputHBox, null, event -> {
            onScorerInsertion();
        });

        createButton("Delete", 100, 30, goalsInputHBox, null, event -> {
            ScorersPojo scorersPojo = goalsTableView.getSelectionModel().getSelectedItem();

            if (scorersPojo == null) {
                return;
            }

            scorersPojo.getMatch().getScorers().remove(scorersPojo.getPlayer().Id);
            goalsTableView.getItems().remove(scorersPojo);
            goalsTableView.refresh();
        });

        addBackButton();

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            onMatchSelection(newSelection);
        });

        seedMatchTableView();
    }

    private void clearInput() {
        textFields.get("Home Team").clear();
        textFields.get("Away Team").clear();
        comboBoxes.get("Stadium").getSelectionModel().clearSelection();
        comboBoxes.get("Referee").getSelectionModel().clearSelection();
        datePickers.get("Date").setValue(null);
        textFields.get("Time").clear();
    }

    private void clearGoalInput() {
        comboBoxes.get("Player").getSelectionModel().clearSelection();
        textFields.get("Goals").clear();
    }

    private void seedMatchTableView() {
        Iterator<Match> MatchIterator = ApplicationRepository.getRepository().getMatchesIterator();
        while (MatchIterator.hasNext()) {
            tableView.getItems().add(new MatchPojo(MatchIterator.next()));
        }
    }
}

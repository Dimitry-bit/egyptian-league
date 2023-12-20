package com.github.egyptian_league.GUI;

import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Player;
import com.github.egyptian_league.Models.Position;
import com.github.egyptian_league.Models.Team;
import com.github.egyptian_league.POJOs.PlayerPojo;

public class PlayerTableScene extends TableScene<PlayerPojo> {

    @Override
    public void addRow() {
        String name = textFields.get("Name").getText();
        String teamName = textFields.get("Team Name").getText();
        String shirtNumberStr = textFields.get("Shirt Number").getText();
        LocalDate birthday = datePickers.get("Birthday").getValue();

        try {
            if (name.isBlank() || teamName.isBlank() || shirtNumberStr.isBlank()) {
                return;
            }

            Position position = (Position) comboBoxes.get("Position").getSelectionModel().getSelectedItem();
            int shirtNumber = Integer.parseInt(shirtNumberStr);

            if (!ApplicationRepository.getRepository().containsTeamName(teamName)) {
                // TODO: Show error
                return;
            }

            Team team = ApplicationRepository.getRepository().getTeamsByName(teamName)[0];

            Player player = new Player(name, team.Id, birthday, position, shirtNumber);
            ApplicationRepository.getRepository().putPlayer(player);

            team.addPlayer(player.Id);

            tableView.getItems().add(new PlayerPojo(player));

            clearInput();
        } catch (Exception e) {
            // TODO: Recover from exceptions
            System.err.printf("Invalid data, %s", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createTextField("Name", 100, 30, inputHBox, null);
        createTextField("Team Name", 100, 30, inputHBox, null);
        createTextField("Shirt Number", 100, 30, inputHBox, null);

        createComboBox("Position", Position.values(), 100, 30, inputHBox, null);

        createDatePicker("Birthday", 100, 30, inputHBox, null);

        addInsertButton("Insert");
        addDeleteButton("Delete");

        createTableColumn("Name", String.class, tableView);
        createTableColumn("TeamName", String.class, tableView);
        createTableColumn("Birthday", LocalDate.class, tableView);
        createTableColumn("Age", Integer.class, tableView);
        createTableColumn("Position", Position.class, tableView);
        createTableColumn("ShirtNumber", Integer.class, tableView);
        createTableColumn("Rank", Integer.class, tableView);

        seedPlayersTableView();
    }

    private void seedPlayersTableView() {
        Iterator<Player> playersIterator = ApplicationRepository.getRepository().getPlayersIterator();
        while (playersIterator.hasNext()) {
            tableView.getItems().add(new PlayerPojo(playersIterator.next()));
        }
    }

    private void clearInput() {
        textFields.get("Name").clear();
        textFields.get("Team Name").clear();
        textFields.get("Shirt Number").clear();
        datePickers.get("Birthday").setValue(null);
        comboBoxes.get("Position").getSelectionModel().clearSelection();
    }
}

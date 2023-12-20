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

import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class PlayerTableScene extends TableScene<PlayerPojo> {

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

            if (position == null) {
                GuiUtils.showAlert("Incomplete Data", "Select position.", AlertType.WARNING);
                return;
            }

            if (!ApplicationRepository.getRepository().containsTeamName(teamName)) {
                GuiUtils.showAlert("Input Error", "Team does not exist.", AlertType.ERROR);
                return;
            }

            Team team = ApplicationRepository.getRepository().getTeamsByName(teamName)[0];

            Player player = new Player(name, team.Id, birthday, position, shirtNumber);
            ApplicationRepository.getRepository().putPlayer(player);

            team.addPlayer(player.Id);

            tableView.getItems().add(new PlayerPojo(player));

            clearInput();
        } catch (NumberFormatException e) {
            GuiUtils.showAlert("Input Error", "Invalid shirt number.", AlertType.ERROR);
        } catch (ClassCastException e) {
            GuiUtils.showAlert("Input Error", "Invalid position.", AlertType.ERROR);
        } catch (Exception e) {
            System.err.printf("Error, %s", e.getMessage());
            GuiUtils.showAlert("Error", e.getMessage(), AlertType.ERROR);
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

        createButton("Insert", 100, 30, inputHBox, null, event -> {
            addRow();
        });

        createButton("Delete", 100, 30, inputHBox, null, event -> {
            PlayerPojo pojo = tableView.getSelectionModel().getSelectedItem();

            if (pojo == null) {
                return;
            }

            Player player = pojo.getPlayer();
            ApplicationRepository.getRepository().removePlayer(player.Id);
            player.getTeam().removePlayer(player.Id);

            tableView.getItems().remove(pojo);
            tableView.getSelectionModel().clearSelection();
            tableView.refresh();
        });

        TableColumn<PlayerPojo, String> nameColumn = createTableColumn("Name", String.class, tableView);
        assignColumnOnEditCommit(nameColumn, TextFieldTableCell.forTableColumn(), event -> {
            Player player = event.getRowValue().getPlayer();
            player.setName(event.getNewValue());

            tableView.refresh();
        });

        TableColumn<PlayerPojo, String> teamColumn = createTableColumn("TeamName", String.class, tableView);
        assignColumnOnEditCommit(teamColumn, TextFieldTableCell.forTableColumn(), event -> {
            Player player = event.getRowValue().getPlayer();

            if (ApplicationRepository.getRepository().containsTeamName(event.getNewValue())) {
                Team team = ApplicationRepository.getRepository().getTeamsByName(event.getNewValue())[0];

                if (!player.setTeamId(team.Id)) {
                    GuiUtils.showAlert("Error", "Failed to set team", AlertType.ERROR);
                }
            } else {
                GuiUtils.showAlert("Input Error", "Team does not exist.", AlertType.ERROR);
            }

            tableView.refresh();
        });

        TableColumn<PlayerPojo, LocalDate> birthdayColumn = createTableColumn("Birthday", LocalDate.class, tableView);
        assignColumnOnEditCommit(birthdayColumn, TextFieldTableCell.forTableColumn(new LocalDateStringConverter()),
                event -> {
                    event.getRowValue().getPlayer().setBirthDay(event.getNewValue());
                    tableView.refresh();
                });

        createTableColumn("Age", Integer.class, tableView);
        TableColumn<PlayerPojo, Position> positionColumn = createTableColumn("Position", Position.class, tableView);
        assignColumnOnEditCommit(positionColumn, TextFieldTableCell.forTableColumn(new PositionConverter()), event -> {
            if (event.getNewValue() != null) {
                event.getRowValue().getPlayer().setPosition(event.getNewValue());
            } else {
                GuiUtils.showAlert("Input Error", "Invalid position.", AlertType.ERROR);
            }

            tableView.refresh();
        });

        TableColumn<PlayerPojo, Integer> shirtNumberColumn = createTableColumn("ShirtNumber", Integer.class, tableView);
        assignColumnOnEditCommit(shirtNumberColumn, TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                event -> {
                    if (!event.getRowValue().getPlayer().setShirtNumber(event.getNewValue())) {
                        GuiUtils.showAlert("Input Error", "Invalid shirt number.", AlertType.ERROR);
                    }

                    tableView.refresh();
                });

        createTableColumn("Rank", Integer.class, tableView);

        backButton.setOnAction(event -> {
            HomePageController.s_switchToHomePage(event);
        });

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

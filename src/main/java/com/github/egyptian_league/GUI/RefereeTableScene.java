package com.github.egyptian_league.GUI;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Referee;
import com.github.egyptian_league.POJOs.RefereePojo;

import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;

public class RefereeTableScene extends TableScene<RefereePojo> {

    public void addRow() {
        String name = textFields.get("Name").getText();

        try {
            if (name.isBlank()) {
                return;
            }

            Referee referee = new Referee(name);
            ApplicationRepository.getRepository().putReferee(referee);

            tableView.getItems().add(new RefereePojo(referee));
            clearInput();
        } catch (Exception e) {
            System.err.printf("Error, %s", e.getMessage());
            GuiUtils.showAlert("Error", e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        createTextField("Name", 100, 30, inputHBox, null);

        createButton("Insert", 100, 30, inputHBox, null, event -> {
            addRow();
        });

        createButton("Delete", 100, 30, inputHBox, null, event -> {
            RefereePojo pojo = tableView.getSelectionModel().getSelectedItem();

            if (pojo == null) {
                return;
            }

            Referee referee = pojo.getReferee();
            ApplicationRepository.getRepository().removeReferee(referee);

            tableView.getItems().remove(pojo);
            tableView.getSelectionModel().clearSelection();
            tableView.refresh();
        });

        TableColumn<RefereePojo, String> nameColumn = createTableColumn("Name", String.class, tableView);
        assignColumnOnEditCommit(nameColumn, TextFieldTableCell.forTableColumn(), event -> {
            Referee referee = event.getRowValue().getReferee();
            referee.setName(event.getNewValue());

            tableView.refresh();
        });

        addBackButton();

        seedStadiumTableView();
    }

    private void seedStadiumTableView() {
        Iterator<Referee> stadiumIterator = ApplicationRepository.getRepository().getRefereesIterator();
        while (stadiumIterator.hasNext()) {
            tableView.getItems().add(new RefereePojo(stadiumIterator.next()));
        }
    }

    private void clearInput() {
        textFields.get("Name").clear();
    }
}

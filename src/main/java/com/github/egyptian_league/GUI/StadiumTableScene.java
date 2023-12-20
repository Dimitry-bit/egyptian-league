package com.github.egyptian_league.GUI;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Stadium;
import com.github.egyptian_league.POJOs.StadiumPojo;

import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;

public class StadiumTableScene extends TableScene<StadiumPojo> {

    public void addRow() {
        String name = textFields.get("Name").getText();
        String address = textFields.get("Address").getText();

        try {
            if (name.isBlank() || address.isBlank()) {
                return;
            }

            Stadium stadium = new Stadium(name, address);
            ApplicationRepository.getRepository().putStadium(stadium);

            tableView.getItems().add(new StadiumPojo(stadium));
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
        createTextField("Address", 100, 30, inputHBox, null);

        createButton("Insert", 100, 30, inputHBox, null, event -> {
            addRow();
        });

        createButton("Delete", 100, 30, inputHBox, null, event -> {
            StadiumPojo pojo = tableView.getSelectionModel().getSelectedItem();

            if (pojo == null) {
                return;
            }

            Stadium stadium = pojo.getStadium();
            ApplicationRepository.getRepository().removeStadium(stadium);

            tableView.getItems().remove(pojo);
            tableView.getSelectionModel().clearSelection();
            tableView.refresh();
        });

        TableColumn<StadiumPojo, String> nameColumn = createTableColumn("Name", String.class, tableView);
        assignColumnOnEditCommit(nameColumn, TextFieldTableCell.forTableColumn(), event -> {
            Stadium stadium = event.getRowValue().getStadium();
            stadium.setName(event.getNewValue());

            tableView.refresh();
        });

        TableColumn<StadiumPojo, String> addressColumn = createTableColumn("Address", String.class, tableView);
        assignColumnOnEditCommit(addressColumn, TextFieldTableCell.forTableColumn(), event -> {
            Stadium stadium = event.getRowValue().getStadium();
            stadium.setAddress(event.getNewValue());

            tableView.refresh();
        });

        addBackButton();

        seedStadiumTableView();
    }

    private void seedStadiumTableView() {
        Iterator<Stadium> stadiumIterator = ApplicationRepository.getRepository().getStadiumsIterator();
        while (stadiumIterator.hasNext()) {
            tableView.getItems().add(new StadiumPojo(stadiumIterator.next()));
        }
    }

    private void clearInput() {
        textFields.get("Name").clear();
        textFields.get("Address").clear();
    }
}

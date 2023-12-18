package com.github.egyptian_league;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Hashtable;

abstract class TableScene<T> {
    DatePicker date;
    HBox hBox = new HBox();
    VBox vBox = new VBox();
    Hashtable<String, TextField> textFields = new Hashtable<>();
    ArrayList<Button> horizontalButtons = new ArrayList<>();
    ArrayList<Button> verticalButtons = new ArrayList<>();

    final TableView<T> table = new TableView<>();

    abstract void addRow();

    void addButtonToHBox(String name, EventHandler<ActionEvent> event) {
        addButtonToHBox(name, 100, 50, event);
    }

    void addButtonToHBox(String name, int width, int height, EventHandler<ActionEvent> event) {
        Button button = new Button();

        button.setText(name);
        button.setMaxWidth(width);
        button.setMaxHeight(height);
        button.setOnAction(event);

        hBox.getChildren().add(button);
        horizontalButtons.add(button);
    }

    void addTextField(String textIn) {
        TextField text = new TextField();

        text.setPromptText(textIn);
        text.setMaxWidth(100);
        textFields.put(textIn, text);
        hBox.getChildren().add(text);
    }

    <C> void addColumn(String columnName, Class<C> c) {
        TableColumn<T, C> column = new TableColumn<>(columnName);

        column.setMinWidth(100);
        column.setCellValueFactory(new PropertyValueFactory<>(columnName));
        table.getColumns().add(column);
    }

    Scene showScene() {
        table.setLayoutX(100);

        // hBox.getChildren().add(date);
        hBox.setMaxWidth(table.getPrefWidth());
        hBox.setLayoutY(400);
        hBox.setLayoutX(0);

        ScrollPane scrollPane = new ScrollPane(table);
        AnchorPane anchorP = new AnchorPane(scrollPane, table, vBox, hBox);

        anchorP.setPrefWidth(450);
        anchorP.setPrefHeight(500);

        return new Scene(new Group(anchorP), 1000, 1000);
    }

    void addDate(String label) {
        date = new DatePicker();
        hBox.getChildren().add(date);

//        Label l = new Label(label);
//        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent e) {
//                LocalDate i = date.getValue();
//                l.setText("Date :" + i);
//            }
//        };
    }

    void clearInput() {
        for (TextField textField : textFields.values()) {
            textField.clear();
        }
    }

    void addDeleteButton(String buttonLabel) {
        addButtonToHBox(buttonLabel, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
            }
        });
    }

    void addInsertButton(String buttonLabel) {
        addButtonToHBox(buttonLabel, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                addRow();
            }
        });
    }
}

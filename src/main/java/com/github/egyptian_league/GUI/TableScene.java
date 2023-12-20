package com.github.egyptian_league.GUI;

import java.util.Hashtable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class TableScene<T> implements Initializable {

    @FXML
    TableView<T> tableView;
    @FXML
    HBox inputHBox;
    @FXML
    VBox switchVBox;

    Hashtable<String, TextField> textFields = new Hashtable<>();
    Hashtable<String, DatePicker> datePickers = new Hashtable<>();
    Hashtable<String, ComboBox<?>> comboBoxes = new Hashtable<>();

    abstract void addRow();

    Button createButton(String name, int width, int height, HBox hBox, VBox vBox, EventHandler<ActionEvent> event) {
        Button button = new Button();

        button.setText(name);
        button.setMaxWidth(width);
        button.setMaxHeight(height);
        button.setOnAction(event);

        if (hBox != null) {
            hBox.getChildren().add(button);
        }

        if (vBox != null) {
            vBox.getChildren().add(button);
        }

        return button;
    }

    TextField createTextField(String label, int width, int height, HBox hBox, VBox vBox) {
        TextField text = new TextField();

        text.setPromptText(label);
        text.setMaxWidth(width);
        text.setMaxHeight(height);

        textFields.put(label, text);

        if (hBox != null) {
            hBox.getChildren().add(text);
        }

        if (vBox != null) {
            vBox.getChildren().add(vBox);
        }

        return text;
    }

    DatePicker createDatePicker(String label, int width, int height, HBox hBox, VBox vBox) {
        DatePicker datePicker = new DatePicker();

        datePicker.setMaxWidth(width);
        datePicker.setMaxHeight(height);

        datePickers.put(label, datePicker);

        if (hBox != null) {
            hBox.getChildren().add(datePicker);
        }

        if (vBox != null) {
            vBox.getChildren().add(datePicker);
        }

        return datePicker;
    }

    <K, V> TableColumn<K, V> createTableColumn(String columnName, Class<V> c, TableView<K> tableView) {
        TableColumn<K, V> column = new TableColumn<>(columnName);

        column.setMinWidth(100);
        column.setCellValueFactory(new PropertyValueFactory<>(columnName));
        tableView.getColumns().add(column);

        return column;
    }

    <V> ComboBox<V> createComboBox(String label, V[] values, int width, int height, HBox hBox, VBox vBox) {
        ComboBox<V> comboBox = new ComboBox<>();

        comboBox.setPromptText(label);
        comboBox.setMaxWidth(width);
        comboBox.setMaxHeight(height);

        for (V v : values) {
            comboBox.getItems().add(v);
        }

        comboBoxes.put(label, comboBox);

        if (hBox != null) {
            hBox.getChildren().add(comboBox);
        }

        if (vBox != null) {
            vBox.getChildren().add(comboBox);
        }

        return comboBox;
    }

    Button addInsertButton(String label) {
        return createButton(label, 100, 30, inputHBox, null, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                addRow();
            }
        });
    }

    Button addDeleteButton(String label) {
        return createButton(label, 100, 30, inputHBox, null, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
            }
        });
    }
}

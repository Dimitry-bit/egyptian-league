package com.github.egyptian_league;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class addscene {
    TilePane r = new TilePane();
    Label l = new Label("l");
    DatePicker date ;
    private final TableView table = new TableView();
    HBox buttonhbox = new HBox();
    VBox vbox = new VBox();
    ArrayList<TextField> Text = new ArrayList<>();
    ArrayList<Button> Hbuttons = new ArrayList<>();
    public ArrayList<Button> Vbuttons = new ArrayList<>();
    public void addBottonToHbox(String name, String type, String Class) {
        Button button = new Button();
        button.setText(name);
        button.setMaxWidth(50);
        if (type.equals("delete")) {
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
                }
            });
            buttonhbox.getChildren().add(button);
        } else if (type.equals("add")) {
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    addrow(Class);
                }
            });
            buttonhbox.getChildren().add(button);
        }
    }


    public void addtexttoHbox() {
        for (TextField text : Text) {
            buttonhbox.getChildren().addAll(text);
        }
    }

//    public void addbuttonHbox() {
//        buttonhbox.setLayoutX(0);
//        for (Button buttn : Hbuttons) {
//            buttonhbox.getChildren().addAll(buttn);
//        }
//    }


    public void addVbox() {
        vbox.setLayoutX(400);
        for (Button button : Vbuttons) {
            vbox.getChildren().addAll(button);
        }
    }
    public void addbVbox(String d) {
       Button b=new Button();
       b.setText(d);
        Vbuttons.add(b);
    }

    public void addTextfield(String textIn) {
        TextField text = new TextField();
        text.setPromptText(textIn);
        text.setMaxWidth(100);
        Text.add(text);

    }


    public <T, C> void addColumn(Class<T> clazz, String columnName, Class<C> c) {
        TableColumn<T, C> column = new TableColumn<>(columnName);
        column.setMinWidth(100);
        column.setCellValueFactory(new PropertyValueFactory<>(columnName));
        table.getColumns().addAll(column);
    }


    public void addrow(String Class) {
        if (Class.equals("p")) {

            String name = Text.get(0).getText();
            String uu = Text.get(1).getText();
            UUID uuid = UUID.fromString(uu);

            String positionText = Text.get(2).getText();
            Position position = Position.valueOf(positionText);
            int number = Integer.parseInt(Text.get(3).getText());
            Player player = new Player(name, uuid, position, number);
            table.getItems().add(player);
        } else if (Class.equals("m")) {
            String tex = Text.get(0).getText();
            UUID uuiD = UUID.fromString(tex);
            String u = Text.get(1).getText();
            UUID Uuid = UUID.fromString(u);
            Match match = new Match(uuiD, Uuid);
            table.getItems().add(match);
        }
    }

    public Scene showScene() {
        buttonhbox.getChildren().addAll(date);
        table.setLayoutX(100);
        ScrollPane scrollPane = new ScrollPane(table);
        buttonhbox.setMaxWidth(table.getPrefWidth());
        buttonhbox.setLayoutY(400);
        buttonhbox.setLayoutX(0);
        AnchorPane anchorP = new AnchorPane(scrollPane, table, vbox, buttonhbox);
        anchorP.setPrefWidth(450);
        anchorP.setPrefHeight(500);
        Scene scene = new Scene(new Group(anchorP), 1000, 1000);
        return scene;
    }
    public void add_date(){
        date=new DatePicker();
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            LocalDate i = date.getValue();
            l.setText("Date :" + i);
        }
    };
}
}


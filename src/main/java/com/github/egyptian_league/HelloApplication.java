package com.github.egyptian_league;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Date;
import java.util.UUID;
public class HelloApplication extends Application {
    public final ObservableList<Player> data = FXCollections.observableArrayList(
    );
    public final ObservableList<Match> matchesdata = FXCollections.observableArrayList(

    );
    public final ObservableList<Team> teams_data = FXCollections.observableArrayList(
    );
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        final Label label = new Label("Ranked players");
        stage.setTitle("Egyptian League");
        addscene scene1=new addscene();
        scene1.addTextfield("Team1");
        scene1.addTextfield("Team2");
        scene1.addTextfield("refree");
        scene1.addTextfield("Stadium");
        scene1.add_date();
        scene1.addTextfield("score");
        scene1.addColumn(Match.class,"Team1", UUID.class);
        scene1.addColumn(Match.class,"Team2",UUID.class);
        scene1.addColumn(Match.class,"refree",Referee.class);
        scene1.addColumn(Match.class,"stadium",Stadium.class);
        scene1.addColumn(Match.class,"date",Date.class);
        scene1.addColumn(Match.class,"goalscores",String.class);
        scene1.addtexttoHbox();
        scene1.addBottonToHbox("add","add","m");
        scene1.addBottonToHbox("delete","delete","m");
        addscene scene2=new addscene();
        scene2.addTextfield("id");
        scene2.addTextfield("name");
        scene2.addTextfield("team");
        scene2.addTextfield("position");
        scene2.addTextfield("number");
        scene2.addTextfield("goals");
        scene2.addTextfield("position");
        scene2.addBottonToHbox("add","add","p");
        scene2.addBottonToHbox("delete","delete","p");
        scene2.addColumn(Player.class,"name",String.class);
        scene2.addColumn(Player.class,"team",String.class);
        scene2.addColumn(Player.class,"position",Integer.class);
        scene2.addColumn(Player.class,"number",Integer.class);
        stage.setHeight(500);
        stage.setResizable(true);
        stage.setScene(scene1.showScene());
        stage.show();
    }
}

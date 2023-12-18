package com.github.egyptian_league;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Egyptian League");

        MatchTableScene matchScene = new MatchTableScene();
        PlayerTableScene playerScene = new PlayerTableScene();

        stage.setHeight(500);
        stage.setResizable(true);
        stage.setScene(playerScene.showScene());
        stage.show();
    }
}

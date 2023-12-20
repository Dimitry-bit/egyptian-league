package com.github.egyptian_league.GUI;
import javafx.application.Application;
import javafx.stage.Stage;
public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
//    public static final Stage stage=new Stage();
@Override
    public void start(Stage stage) {
        stage.setTitle("Egyptian League");
        stage.setHeight(500);
        stage.setResizable(true);
        stage.setScene(PlayerTableScene.getplayer_table_scene().showScene());
        stage.show();
    }
}
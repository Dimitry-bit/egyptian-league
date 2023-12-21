package com.github.egyptian_league.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GuiApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(GuiApplication.class.getResourceAsStream("/5336007-middle.png")));
            stage.setTitle("Egyptian League");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

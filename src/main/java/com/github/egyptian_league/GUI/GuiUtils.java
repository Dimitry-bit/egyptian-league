package com.github.egyptian_league.GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GuiUtils {
    private GuiUtils() {
    }

    public static Alert showAlert(String title, String contentText, AlertType type) {
        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.showAndWait();

        return alert;
    }
}

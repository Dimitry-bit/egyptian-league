package com.github.egyptian_league.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageController {

    @FXML
    public void switchToTeamsPage(ActionEvent event) {
        s_switchToTeamsPage(event);
    }

    @FXML
    public void switchToHomePage(ActionEvent event) {
        s_switchToHomePage(event);
    }

    @FXML
    public void switchToPlayerPage(ActionEvent event) {
        s_switchToPlayerPage(event);
    }

    @FXML
    public void switchToMatchPage(ActionEvent event) {
        s_switchToMatchPage(event);
    }

    @FXML
    public void switchToStadiumPage(ActionEvent event) {
        s_switchToStadiumPage(event);
    }

    @FXML
    public void switchToRefereePage(ActionEvent event) {
        s_switchToRefereePage(event);
    }

    public static void s_switchToTeamsPage(ActionEvent event) {
        switchPage("/TeamsPage.fxml", event);
    }

    public static void s_switchToHomePage(ActionEvent event) {
        switchPage("/HomePage.fxml", event);
    }

    public static void s_switchToPlayerPage(ActionEvent event) {
        switchPage("/PlayersPage.fxml", event);
    }

    public static void s_switchToMatchPage(ActionEvent event) {
        switchPage("/MatchesPage.fxml", event);
    }

    public static void s_switchToStadiumPage(ActionEvent event) {
        switchPage("/StadiumPage.fxml", event);
    }

    public static void s_switchToRefereePage(ActionEvent event) {
        switchPage("/RefereePage.fxml", event);
    }

    private static void switchPage(String pagePath, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(HomePageController.class.getResource(pagePath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e.getMessage());
        }
    }
}
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
        try {
            Parent root = FXMLLoader.load(HomePageController.class.getResource("/TeamsPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e.getMessage());
        }
    }

    public static void s_switchToHomePage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(HomePageController.class.getResource("/HomePage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e.getMessage());
        }
    }

    public static void s_switchToPlayerPage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(HomePageController.class.getResource("/PlayersPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e.getMessage());
        }
    }

    public static void s_switchToMatchPage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(HomePageController.class.getResource("/MatchesPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e.getMessage());
        }
    }

    public static void s_switchToStadiumPage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(HomePageController.class.getResource("/StadiumPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e.getMessage());
        }
    }

    public static void s_switchToRefereePage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(HomePageController.class.getResource("/RefereePage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e.getMessage());
        }
    }
}
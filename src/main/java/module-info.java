module com.github.egyptian_league {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.github.egyptian_league to javafx.fxml;
    exports com.github.egyptian_league;
}
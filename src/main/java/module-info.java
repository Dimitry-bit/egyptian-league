module com.github.egyptian_league {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.github.egyptian_league to javafx.fxml;
    opens com.github.egyptian_league.GUI to javafx.fxml;
    opens com.github.egyptian_league.POJOs to javafx.fxml;

    exports com.github.egyptian_league.POJOs;
    exports com.github.egyptian_league.GUI;
    exports com.github.egyptian_league.Models;

    exports com.github.egyptian_league;
    opens com.github.egyptian_league.Models to javafx.fxml;
    exports com.github.egyptian_league.Json;
    opens com.github.egyptian_league.Json to javafx.fxml;
}

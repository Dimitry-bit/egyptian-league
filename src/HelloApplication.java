import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader);
        stage.setTitle("Egyptain league");
        stage.setScene(scene);
        stage.show();
    }


}
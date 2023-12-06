import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

   public void changeScreenToTeams(ActionEvent event) throws IOException {
       Parent teamsLoader = FXMLLoader.load(getClass().getResource("Teams.fxml"));
       Scene teamsScene = new Scene(teamsLoader);

       Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(teamsScene);
       window.show();

   }
}
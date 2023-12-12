import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Date;


public class HelloApplication extends Application {
    private final ObservableList<player> data = FXCollections.observableArrayList(
            new player("Smith", "344", "gfgdfg", 3, 3, 30, 3, 3)

    );
    private final ObservableList<Match> matchesdata = FXCollections.observableArrayList(
            //error at refree
            //new Match("x", "Smith","jnj","jyjgg","jnkn",6,)
    );
    private final ObservableList<Team> teams_data = FXCollections.observableArrayList(
//            new Team("name", 3, 4,"uon");

    );
    private final TableView<player> player_t = new TableView<>();
    private final TableView<Match> tablematches = new TableView<>();
    private final TableView<Team> teams = new TableView<>();
    public Button players_b = new Button();
    public Button teamsView = new Button();
    TextField matchesteam1 = new TextField();
    TextField matchesteam2 = new TextField();
    TextField matchesId = new TextField();
    TextField matchesdate = new TextField();
    TextField matchesscore = new TextField();
    TextField matchesstad = new TextField();
    TextField matchesref = new TextField();
    private final Button add = new Button();
    private final Button delete = new Button();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Egyptian League");


        TableColumn<Match, String> team1 = new TableColumn<>("team1");
        team1.setMinWidth(100);
        team1.setCellValueFactory(new PropertyValueFactory<>("team1"));

        TableColumn<Match, String> team2 = new TableColumn<>("team2");
        team2.setMinWidth(100);
        team2.setCellValueFactory(new PropertyValueFactory<>("team2"));

        TableColumn<Match, Date> mdate = new TableColumn<>("date");
        mdate.setMinWidth(100);
        mdate.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Match, Integer> score = new TableColumn<>("Score");
        score.setMinWidth(100);
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));


        TableColumn<Match, String> stadiumName = new TableColumn<>("stadiumName ");
        stadiumName.setMinWidth(100);
        stadiumName.setCellValueFactory(new PropertyValueFactory<>("stadiumName"));
        TableColumn<Match, Referee> mref = new TableColumn<>("refree ");
        mref.setMinWidth(100);
        mref.setCellValueFactory(new PropertyValueFactory<>("refree"));
        TableColumn<Match, String> mid = new TableColumn<>("id ");
        mid.setMinWidth(100);
        mid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablematches.setItems(matchesdata);
        tablematches.getColumns().addAll(team1, team2, stadiumName, mdate,mref,score);


        TableColumn<Team, String> TID = new TableColumn<>("ID");
        TID.setMinWidth(100);
        TID.setCellValueFactory(new PropertyValueFactory<>("ID"));


        TableColumn<Team, String> NAME = new TableColumn<>("NAME");
        NAME.setMinWidth(100);
        NAME.setCellValueFactory(new PropertyValueFactory<>("NAME"));
        TableColumn<Team, Integer> Total_s = new TableColumn<>("TotalScore");
        Total_s.setMinWidth(100);
        Total_s.setCellValueFactory(new PropertyValueFactory<>("TotalScore"));


        teams.getColumns().addAll(NAME, TID,Total_s);
        teams.setItems(teams_data);


        Total_s.setSortType(TableColumn.SortType.ASCENDING);

        add.setText("add");
        add.setLayoutX(30);
        add.setLayoutY(440);

        Total_s.setComparator(Total_s.getComparator());
        Total_s.setSortable(true);
        TableColumn<Team, String> totalpointsc = new TableColumn<>("points");


        player_t.setPrefWidth(800);
        TableColumn<player, Integer> rankedCol = new TableColumn<>("Rank");
        rankedCol.setMinWidth(100);
        rankedCol.setCellValueFactory(new PropertyValueFactory<>("Rank"));
        TableColumn<player, String> NameCol = new TableColumn<>("name");
        NameCol.setMinWidth(100);
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<player, Integer> agep = new TableColumn<>("age");
        agep.setMinWidth(100);
        agep.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<player, String> positionp = new TableColumn<>("position");
        positionp.setMinWidth(100);
        positionp.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<player, Integer> no = new TableColumn<>("number");
        no.setMinWidth(100);
        no.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<player, String> t_c = new TableColumn<>("team");
        t_c.setMinWidth(100);
        t_c.setCellValueFactory(new PropertyValueFactory<>("team"));

        TableColumn<player, Integer> goals = new TableColumn<>("GoalScored");
        goals.setMinWidth(100);
        goals.setCellValueFactory(new PropertyValueFactory<>("GoalScored"));

        TableColumn<player, Integer> idp = new TableColumn<>("id");
        rankedCol.setMinWidth(100);
        rankedCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        player_t.setItems(data);
        player_t.getColumns().addAll(rankedCol, NameCol, t_c, no, goals, idp, positionp, agep);


        matchesteam1.setPromptText("team 1");
        matchesteam2.setPromptText(" team 2");
        matchesId.setPromptText("id");
        matchesscore.setPromptText(" score");
        matchesdate.setPromptText("date");
        matchesstad.setPromptText("stad");
        matchesref.setPromptText("ref");



        matchesteam1.setMaxWidth(team1.getPrefWidth());
        matchesteam2.setMaxWidth(team2.getPrefWidth());
        matchesdate.setMaxWidth(team1.getPrefWidth());
        matchesref.setMaxWidth(team1.getPrefWidth());
        matchesId.setMaxWidth(team1.getPrefWidth());
        matchesstad.setMaxWidth(team1.getPrefWidth());
        matchesscore.setMaxWidth(team1.getPrefWidth());
        HBox hbox = new HBox();
        hbox.getChildren().addAll(matchesteam1, matchesteam2, matchesdate, matchesref, matchesId, matchesstad, matchesscore);
hbox.setLayoutY(400);
        delete.setText("delete");
        delete.setLayoutY(460);
        delete.setLayoutX(110);


        totalpointsc.setMinWidth(100);
        totalpointsc.setCellValueFactory(new PropertyValueFactory<>("total_points"));



        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tablematches.getItems().removeAll(tablematches.getSelectionModel().getSelectedItem());
            }
        });


//        add.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                if (matchesteam1.getText().isEmpty() || matchesteam2.getText().isEmpty()) {
//                    System.out.println("error");
//                } else {
//                    try {
//                        int id = Integer.parseInt(matchesId.getText());
//                        int score = Integer.parseInt(matchesscore.getText());
//                        String team1 = matchesteam1.getText();
//                        String team2 = matchesteam2.getText();
//
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                        Date date = dateFormat.parse(matchesdate.getText());
//
//                        matchesdata.add(new Match(
//                                id,
//                                date,
//                                team1,
//                                team2,
//                                matchesstad.getText(),
//                                score,
//                                new Referee(matchesref.getText())
//                        ));
//                    } catch (NumberFormatException ex) {
//                        System.out.println("Invalid ID or score format");
//                    } catch (ParseException ex) {
//                        System.out.println("Invalid date format");
//                    }
//                    matchesteam1.clear();
//                    matchesteam2.clear();
//                }
//            }
//        });
        stage.setWidth(450);
        stage.setHeight(500);
        stage.setResizable(true);

//        Scene scene2 = new Scene(layout2, 300, 300);
        final Label label = new Label("Ranked players");
        label.setFont(new Font("Arial", 20));
        final Button matches = new Button();
        matches.setText("matches");
//matches.setScaleX(200);
//
//matches.setScaleY(120);
        players_b.setText("players");
        players_b.setLayoutX(100);
        players_b.setLayoutY(450);
        matches.setLayoutX(0);
        matches.setLayoutY(400);
        players_b.autosize();
        matches.autosize();
        matches.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {

                VBox layout3 = new VBox();
                // Create table columns
                ScrollPane scrollPane = new ScrollPane(tablematches); // Wrap the table in a ScrollPane
                AnchorPane anchorPane = new AnchorPane(scrollPane, tablematches, players_b, teamsView, add, matchesteam2, matchesteam1, delete);
                anchorPane.prefWidthProperty().bind(stage.widthProperty()); // Bind the AnchorPane's width to the stage's width
                anchorPane.prefHeightProperty().bind(stage.heightProperty()); // Bind the AnchorPane's height to the stage's height
                Scene scene2 = new Scene(new Group(anchorPane), 200, 400);
                stage.setScene(scene2);
            }
        });
        teamsView.setText("teams");
        teamsView.setLayoutY(400);
        teamsView.setLayoutX(10);


        ScrollPane scrollPane = new ScrollPane(player_t); // Wrap the table in a ScrollPane

        AnchorPane AnchorPane = new AnchorPane(scrollPane, matches);
        AnchorPane.prefWidthProperty().bind(stage.widthProperty()); // Bind the StackPane's width to the stage's width
        AnchorPane.prefHeightProperty().bind(stage.heightProperty()); // Bind the StackPane's height to the stage's height


        Scene scene = new Scene(new Group(AnchorPane), 200, 400); // Use the StackPane as the root node of the scene

        players_b.setOnAction(event -> stage.setScene(scene));
        teamsView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                AnchorPane x = new AnchorPane(scrollPane, teams);
                x.prefWidthProperty().bind(stage.widthProperty()); // Bind the StackPane's width to the stage's width
                x.prefHeightProperty().bind(stage.heightProperty()); // Bind the StackPane's height to the stage's height
                Scene scene3 = new Scene(new Group(x), 300, 300);
                teams.sort();


                stage.setScene(scene3);
            }
        });
        stage.setScene(scene);
        stage.show();
    }
}
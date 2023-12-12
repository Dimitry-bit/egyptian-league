package com.github.egyptian_league;

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
    private final ObservableList<Player> data = FXCollections.observableArrayList(
//            new player("Smith", "344", "gfgdfg", 3, 3, 30, 3, 3)
    );
    private final ObservableList<Match> matchesdata = FXCollections.observableArrayList(
            //error at refree
            //new Match("x", "Smith","jnj","jyjgg","jnkn",6,)
    );
    private final ObservableList<Team> teams_data = FXCollections.observableArrayList(
            //  new Team("name", 3, 4);

    );
    private final TableView<Player> player_t = new TableView<>();
    private final TableView<Match> tablematches = new TableView<>();
    private final TableView<Team> teams = new TableView<>();
    TextField matchesteam1 = new TextField();
    TextField matchesteam2 = new TextField();
    TextField matchesId = new TextField();
    TextField matchesdate = new TextField();
    TextField matchesscore = new TextField();
    TextField matchesstad = new TextField();
    TextField matchesref = new TextField();
    TextField pname = new TextField();
    TextField pteam = new TextField();
    TextField pposition = new TextField();
    TextField pid = new TextField();
    TextField pno = new TextField();
    TextField p_age = new TextField();
    TextField pgoal = new TextField();
    TextField prank = new TextField();
    TextField tname = new TextField();
    TextField tid = new TextField();
    TextField tscore = new TextField();
    TextField tcap = new TextField();

    HBox pbox=new HBox();
    public Button players_b = new Button();
    public Button teamsView = new Button();
    private final Button add = new Button();
    private final Button delete = new Button();
    final Button matches = new Button();

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


        TableColumn<Team, String> TID = new TableColumn<>("id");
        TID.setMinWidth(100);
        TID.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Team, Player> tc = new TableColumn<>("captain");
        tc.setMinWidth(100);
        tc.setCellValueFactory(new PropertyValueFactory<>("Captain"));

        TableColumn<Team, String> NAME = new TableColumn<>("NAME");
        NAME.setMinWidth(100);
        NAME.setCellValueFactory(new PropertyValueFactory<>("NAME"));
        TableColumn<Team, Integer> Total_s = new TableColumn<>("TotalScore");
        Total_s.setMinWidth(100);
        Total_s.setCellValueFactory(new PropertyValueFactory<>("TotalScore"));


        teams.getColumns().addAll(NAME, TID,Total_s,tc);
        teams.setItems(teams_data);


        Total_s.setSortType(TableColumn.SortType.ASCENDING);

        add.setText("add");
        add.setLayoutX(30);
        add.setLayoutY(440);
        teams.setPrefWidth(400);
        Total_s.setComparator(Total_s.getComparator());
        Total_s.setSortable(true);
        TableColumn<Team, String> totalpointsc = new TableColumn<>("points");
        player_t.setPrefWidth(800);
        TableColumn<Player, Integer> rankedCol = new TableColumn<>("Rank");
        rankedCol.setMinWidth(100);
        rankedCol.setCellValueFactory(new PropertyValueFactory<>("Rank"));
        TableColumn<Player, String> NameCol = new TableColumn<>("name");
        NameCol.setMinWidth(100);
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Player, Integer> agep = new TableColumn<>("age");
        agep.setMinWidth(100);
        agep.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Player, String> positionp = new TableColumn<>("position");
        positionp.setMinWidth(100);
        positionp.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<Player, Integer> no = new TableColumn<>("number");
        no.setMinWidth(100);
        no.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Player, String> t_c = new TableColumn<>("team");
        t_c.setMinWidth(100);
        t_c.setCellValueFactory(new PropertyValueFactory<>("team"));

        TableColumn<Player, Integer> goals = new TableColumn<>("GoalScored");
        goals.setMinWidth(100);
        goals.setCellValueFactory(new PropertyValueFactory<>("GoalScored"));

        TableColumn<Player, Integer> idp = new TableColumn<>("ID");
        idp.setMinWidth(100);
        idp.setCellValueFactory(new PropertyValueFactory<>("ID"));
        player_t.setItems(data);
        player_t.getColumns().addAll( NameCol, t_c,positionp, idp, no, agep,goals,rankedCol);
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
        delete.setText("delete");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(matchesteam1, matchesteam2, matchesref,matchesdate, matchesId, matchesstad, matchesscore);
        hbox.setLayoutY(400);


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
        final Label label = new Label("Ranked players");
        label.setFont(new Font("Arial", 20));
        matches.setText("matches");
        players_b.setText("players");

        players_b.autosize();
        matches.autosize();
        teamsView.setText("teams");

        pid.setPromptText("id");
        pno.setPromptText("player number");
        pposition.setPromptText("position");
        pname.setPromptText("name");
        prank.setPromptText("rank");
        pgoal.setPromptText("goals");
        pteam.setPromptText("team");
        p_age.setPromptText("age");
//        pid.setMaxWidth(NameCol.getPrefWidth());
        pbox.setMaxWidth(player_t.getPrefWidth());
        VBox bot=new VBox();
        bot.getChildren().addAll(matches,players_b,teamsView);
        bot.setLayoutY(0);
        bot.setLayoutX(900);
        pbox.getChildren().addAll(pname,pteam,pposition,pid,pno,p_age,pgoal,prank);
        pbox.setLayoutY(410);
        pbox.setLayoutX(0);
        tid.setPromptText("team id");
        tname.setPromptText("name");
        tscore.setPromptText("pionts");
        tcap.setPromptText("captain");
        HBox tbox=new HBox();
        tbox.getChildren().addAll(tname,tid,tscore,tcap);
        tbox.setLayoutY(400);
        tbox.setMaxWidth(teams.getPrefWidth());
        teamsView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ScrollPane scrollPane = new ScrollPane(teams);
                AnchorPane x = new AnchorPane(scrollPane, teams,bot,tbox);

                x.prefWidthProperty().bind(stage.widthProperty()); // Bind the StackPane's width to the stage's width
                x.prefHeightProperty().bind(stage.heightProperty()); // Bind the StackPane's height to the stage's height
                Scene scene3 = new Scene(new Group(x), 300, 300);
                teams.sort();


                stage.setScene(scene3);
            }
        });

        ScrollPane scrollPan = new ScrollPane(tablematches); // Wrap the table in a ScrollPane
        AnchorPane AnchorPane = new AnchorPane(scrollPan,bot);
        AnchorPane.prefWidthProperty().bind(stage.widthProperty()); // Bind the StackPane's width to the stage's width
        AnchorPane.prefHeightProperty().bind(stage.heightProperty()); // Bind the StackPane's height to the stage's height
        matches.setOnAction(new EventHandler<>() {
            VBox layout3 = new VBox();
            @Override
            public void handle(ActionEvent event) {


                ScrollPane scrollPane = new ScrollPane(tablematches); // Wrap the table in a ScrollPane
                AnchorPane anchorPane = new AnchorPane(scrollPane, tablematches, bot,hbox);
                anchorPane.prefWidthProperty().bind(stage.widthProperty()); // Bind the AnchorPane's width to the stage's width
                anchorPane.prefHeightProperty().bind(stage.heightProperty()); // Bind the AnchorPane's height to the stage's height
                Scene scene2 = new Scene(new Group(anchorPane), 200, 400);
                stage.setScene(scene2);
            }
        });
        Scene scene = new Scene(new Group(AnchorPane), 200, 400); // Use the StackPane as the root node of the scene
        players_b.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                ScrollPane scrollPane = new ScrollPane(player_t); // Wrap the table in a ScrollPane
                AnchorPane anchorP = new AnchorPane(scrollPane, player_t,bot ,pbox);
                anchorP.prefWidthProperty().bind(stage.widthProperty()); // Bind the AnchorPane's width to the stage's width
                anchorP.prefHeightProperty().bind(stage.heightProperty()); // Bind the AnchorPane's height to the stage's height
                Scene scene4 = new Scene(new Group(anchorP), 200, 400);
                stage.setScene(scene4);
            }
        });

        stage.setScene(scene);
        stage.show();
    }
}

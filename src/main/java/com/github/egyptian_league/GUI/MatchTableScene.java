package com.github.egyptian_league.GUI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.*;
import com.github.egyptian_league.POJOs.MatchPojo;
import com.github.egyptian_league.POJOs.PlayerPojo;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.ResourceBundle;

public class MatchTableScene extends TableScene<Match> {
    private static final MatchTableScene MATCH_TABLE_SCENE = new MatchTableScene();
    private MatchTableScene() {
    }
    public static MatchTableScene getInstance() {
        return MATCH_TABLE_SCENE;
    }
        @Override
    public void addRow() {
        String homeTeamText = textFields.get("homeTeam").getText();
        String AwayTeamText = textFields.get("AwayTeam").getText();
        String StadiumText = textFields.get("Stadium").getText();
        String RefereeText = textFields.get("Referee").getText();
        LocalDate DateText = datePickers.get("Date").getValue();
        String ScoreText = textFields.get("Score").getText();
        try {
            int scoreText = Integer.parseInt(ScoreText);
            Team[] HOME = ApplicationRepository.getRepository().getTeamsByName(homeTeamText);
            Team[] AWAY = ApplicationRepository.getRepository().getTeamsByName(AwayTeamText);
//            Stadium stadium=ApplicationRepository


//            Match match = new Match(HOME[0], AWAY[0],);

        } catch (Exception e) {
            // TODO: handle exception
            System.err.printf("Invalid data, %s", e.getMessage());
            e.printStackTrace();
        }
    }
    private void seedMatchTableView() {
        Iterator<Match> MatchIter = ApplicationRepository.getRepository().getMatchesIterator();
        while (MatchIter.hasNext()) tableView.getItems().add(new MatchPojo(MatchIter.next()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTextField("HomeTeam1",100, 30, inputHBox, null);
        createTextField("AwayTeam2",100, 30, inputHBox, null);
        createTextField("Stadium",100, 30, inputHBox, null);
        createTextField("Referee",100, 30, inputHBox, null);
        createDatePicker("Date",100, 30, inputHBox, null);
        createTextField("Score",100, 30, inputHBox, null);
        addInsertButton("Insert");
        addDeleteButton("Delete");
        addInsertButton("players");
        createTableColumn("HomeTeam", Team.class,tableView);
        createTableColumn("AwayTeam", Team.class,tableView);
        createTableColumn("Stadium", Stadium.class,tableView);
        createTableColumn("Referee", Referee.class,tableView);
        createTableColumn("Date", LocalDateTime.class,tableView);
        createTableColumn("Score", Integer.class,tableView);

    }
    private void clearInput() {
        textFields.get("HomeTeam1").clear();
        textFields.get("AwayTeam2").clear();
        textFields.get("Stadium").clear();
        textFields.get("Referee").clear();
        datePickers.get("Date").setValue(null);
        textFields.get("Score").clear();

    }
}

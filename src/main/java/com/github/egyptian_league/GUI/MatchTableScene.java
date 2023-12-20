package com.github.egyptian_league.GUI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Match;
import com.github.egyptian_league.Models.Referee;
import com.github.egyptian_league.Models.Stadium;
import com.github.egyptian_league.Models.Team;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MatchTableScene extends TableScene<Match> {
    private static final MatchTableScene MATCH_TABLE_SCENE = new MatchTableScene();
    private MatchTableScene() {
        addTextField("HomeTeam1");
        addTextField("AwayTeam2");
        addTextField("Stadium");
        addTextField("Referee");
        addDate("Date");
        addTextField("Score");
        addInsertButton("Insert");
        addDeleteButton("Delete");
        addInsertButton("players");
        addswitchButtontoplayer("players");
        addswitchButtontoHomePAge("home page");
        addswitchButtontoTeams("teams ");
        addswitchButtontoLeague("leagues");
        addColumn("HomeTeam", Team.class);
        addColumn("AwayTeam", Team.class);
        addColumn("Stadium", Stadium.class);
        addColumn("Referee", Referee.class);
        addColumn("Date", LocalDateTime.class);
        addColumn("Score", Integer.class);
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
}

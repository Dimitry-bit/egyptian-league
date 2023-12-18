package com.github.egyptian_league;

import java.util.Date;
import java.util.UUID;

public class MatchTableScene extends TableScene<Match> {

    public MatchTableScene() {
        addTextField("Team1");
        addTextField("Team2");
        addTextField("referee");
        addTextField("Stadium");
        addTextField("score");

        addDate("Scene1 Date");

        addInsertButton("Insert");
        addDeleteButton("Delete");

        addColumn("Team1", UUID.class);
        addColumn("Team2", UUID.class);
        addColumn("referee", Referee.class);
        addColumn("stadium", Stadium.class);
        addColumn("date", Date.class);
        addColumn("goalScorers", String.class);
    }

    @Override
    public void addRow() {
        String team1UUIDStr = textFields.get("Team1").getText();
        String team2UUIDStr = textFields.get("Team2").getText();

        try {
            UUID team1UUID = UUID.fromString(team1UUIDStr);
            UUID team2UUID = UUID.fromString(team2UUIDStr);

            Match match = new Match(team1UUID, team2UUID);

            table.getItems().add(match);
        } catch (Exception e) {
            // TODO: handle exception
            System.err.printf("Invalid data, %s", e.getMessage());
            e.printStackTrace();
        }
    }
}

package com.github.egyptian_league;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class Match {
    public final UUID matchId;
    public final UUID HomeTeamId;
    public final UUID AwayTeamId;
    public final Referee Referee; // FIXME: Can it change?
    private LocalDateTime dateTime;
    private HashMap<UUID, Integer> goalScorers;

    public Match(UUID homeTeamId, UUID awayTeamId, Referee Referee) {
        matchId = UUID.randomUUID();
        this.HomeTeamId = homeTeamId;
        this.AwayTeamId = awayTeamId;
        this.Referee = Referee;
    }

    public HashMap<UUID, Integer> getGoalScorers() {
        return goalScorers;
    }

    public void addGoal(UUID playerID, int numOfGoals) {
        goalScorers.put(playerID, numOfGoals);
    }

    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // FIXME: Why?
    public static Boolean isComingDate(LocalDateTime currentDate, LocalDateTime suggestedDate) {
        return currentDate.isAfter(suggestedDate);
    }
}

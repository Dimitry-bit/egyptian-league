package com.github.egyptian_league;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class Match {
    public final UUID matchId;
    public final UUID HomeTeamId;
    public final UUID AwayTeamId;
    private Stadium matchStadium; // FIXME: Use a UUID
    public final Referee Referee; // FIXME: Can it change?
    private LocalDateTime dateTime;
    private HashMap<UUID, Integer> goalScorers;

    public Match(UUID homeTeamId, UUID awayTeamId, Referee Referee) {
        matchId = UUID.randomUUID();
        this.HomeTeamId = homeTeamId;
        this.AwayTeamId = awayTeamId;
        this.Referee = Referee;
    }

    public Stadium getMatchStadium() {
        return matchStadium;
    }

    public void setMatchStadium(Stadium matchStadium) {
        this.matchStadium = matchStadium;
    }

    private void setDate(LocalDateTime dateTime) {
        // FIXME: Validate before setting
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HashMap<UUID, Integer> getGoalScorers() {
        return goalScorers;
    }

    public void addGoal(UUID playerID, int numOfGoals) {
        goalScorers.put(playerID, numOfGoals);
    }

    // FIXME: Why?
    /*
     * public static Boolean isComingDate(LocalDateTime currentDate, LocalDateTime
     * suggestedDate) {
     * return currentDate.isAfter(suggestedDate);
     * }
     */
}

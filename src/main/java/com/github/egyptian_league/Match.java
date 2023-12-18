package com.github.egyptian_league;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

import com.github.egyptian_league.json.Annotations.JsonConstructor;

import javafx.application.Application;

public class Match {
    public final UUID matchId;
    public final UUID HomeTeamId;
    public final UUID AwayTeamId;
    private UUID matchStadium;
    public Referee Referee;
    private LocalDateTime dateTime;
    private HashMap<UUID, Integer> goalScorers;

    @JsonConstructor(parameters = { "homeTeamId", "awayTeamId" })
    public Match(UUID homeTeamId, UUID awayTeamId) {
        matchId = UUID.randomUUID();
        this.HomeTeamId = homeTeamId;
        this.AwayTeamId = awayTeamId;
    }

    public UUID getMatchStadium() {
        return matchStadium;
    }

    public void setMatchStadium(UUID matchStadium) {
        this.matchStadium = matchStadium;
    }

    private void setDate(LocalDateTime dateTime) {
        Stadium stadium = ApplicationRepository.getRepository().getStadiumByUUID(matchStadium);
        if (stadium != null && stadium.checkStadiumAvailability(dateTime)) {
            this.dateTime = dateTime;
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HashMap<UUID, Integer> getGoalScorers() {
        return goalScorers;
    }

    public void addGoal(UUID playerId, int numOfGoals) {
        // FIXME: Validate numbOfGoals before setting
        if (numOfGoals >= 0) {
            goalScorers.put(playerId, numOfGoals);
        }

    }

    public void removeGoal(UUID playerId, int numOfGoals) {
        goalScorers.put(playerId, numOfGoals);
    }

    public Referee setReferee(Referee referee) {
        if (referee.CheckRefereeAvailability(this)) {
            this.Referee = referee;
            return referee;
        }
        return null;
    }

    public UUID calcWinnerTeam() {
        int homeScore = 0;
        int awayScore = 0;
        Iterator<UUID> playerIdIterator = ApplicationRepository.getRepository().getTeamById(HomeTeamId).getPlayers();
        while (playerIdIterator.hasNext()) {
            UUID playerUUID = playerIdIterator.next();
            if (goalScorers.containsKey(playerUUID)) {
                homeScore += goalScorers.get(playerUUID);
            }
        }
        playerIdIterator = ApplicationRepository.getRepository().getTeamById(AwayTeamId).getPlayers();
        while (playerIdIterator.hasNext()) {
            UUID playerUUID = playerIdIterator.next();
            if (goalScorers.containsKey(playerUUID)) {
                awayScore += goalScorers.get(playerUUID);
            }
        }

        if (homeScore > awayScore) {
            return HomeTeamId;
        } else if (homeScore < awayScore)
            return AwayTeamId;
        else
            return null;
    }
}

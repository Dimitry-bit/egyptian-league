package com.github.egyptian_league;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

import com.github.egyptian_league.json.Annotations.JsonConstructor;

import javafx.application.Application;

public class Match {

    public final UUID id;

    private UUID homeTeamId;
    private UUID awayTeamId;
    private UUID stadiumId;
    private Referee referee;
    private LocalDateTime dateTime;
    private HashMap<UUID, Integer> scorers;

    @JsonConstructor(parameters = { "homeTeamId", "awayTeamId" })
    public Match(UUID homeTeamId, UUID awayTeamId) {
        id = UUID.randomUUID();
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }

    public UUID getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(UUID stadiumId) {
        this.stadiumId = stadiumId;
    }

    private void setDate(LocalDateTime dateTime) {
        Stadium stadium = ApplicationRepository.getRepository().getStadiumByUUID(stadiumId);
        if (stadium != null && stadium.checkStadiumAvailability(dateTime)) {
            this.dateTime = dateTime;
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HashMap<UUID, Integer> getScorers() {
        return scorers;
    }

    public void addGoal(UUID playerId, int numOfGoals) {
        // FIXME: Validate numbOfGoals before setting
        if (numOfGoals >= 0) {
            scorers.put(playerId, numOfGoals);
        }

    }

    public void removeGoal(UUID playerId, int numOfGoals) {
        scorers.put(playerId, numOfGoals);
    }

    public Referee setReferee(Referee referee) {
        if (referee.CheckRefereeAvailability(this)) {
            this.referee = referee;
            return referee;
        }
        return null;
    }

    public UUID calcWinnerTeam() {
        int homeScore = 0;
        int awayScore = 0;
        Iterator<UUID> playerIdIterator = ApplicationRepository.getRepository().getTeamById(homeTeamId).getPlayers();
        while (playerIdIterator.hasNext()) {
            UUID playerUUID = playerIdIterator.next();
            if (scorers.containsKey(playerUUID)) {
                homeScore += scorers.get(playerUUID);
            }
        }
        playerIdIterator = ApplicationRepository.getRepository().getTeamById(awayTeamId).getPlayers();
        while (playerIdIterator.hasNext()) {
            UUID playerUUID = playerIdIterator.next();
            if (scorers.containsKey(playerUUID)) {
                awayScore += scorers.get(playerUUID);
            }
        }

        if (homeScore > awayScore) {
            return homeTeamId;
        } else if (homeScore < awayScore)
            return awayTeamId;
        else
            return null;
    }
}

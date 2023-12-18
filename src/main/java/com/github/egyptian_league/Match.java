package com.github.egyptian_league;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import com.github.egyptian_league.json.Annotations.JsonConstructor;

import javafx.application.Application;

public class Match {

    public final UUID id;

    private UUID homeTeamId;
    private UUID awayTeamId;
    private UUID stadiumId;
    private UUID refereeId;
    private LocalDateTime dateTime;
    private HashMap<UUID, Integer> scorers;

    @JsonConstructor(parameters = { "homeTeamId", "awayTeamId", "stadiumId", "refereeId" })
    public Match(UUID homeTeamId, UUID awayTeamId, UUID stadiumId, UUID refereeId) {
        this.id = UUID.randomUUID();
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.stadiumId = stadiumId;
        this.refereeId = refereeId;
    }

    public UUID getStadiumId() {
        return stadiumId;
    }

    public boolean setStadiumId(UUID stadiumId) {
        if (!ApplicationRepository.getRepository().containsStadiumUUID(stadiumId)) {
            return false;
        }

        this.stadiumId = stadiumId;
        return true;
    }

    public boolean setDate(LocalDateTime newDateTime) {
        Stadium stadium = ApplicationRepository.getRepository().getStadiumByUUID(stadiumId);
        if ((stadium == null) || !stadium.checkStadiumAvailability(newDateTime)) {
            return false;
        }

        this.dateTime = newDateTime;
        return true;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Set<Entry<UUID, Integer>> getScorers() {
        return scorers.entrySet();
    }

    public boolean putGoals(UUID playerId, int numOfGoals) {
        if (numOfGoals <= 0) {
            return false;
        }

        scorers.put(playerId, numOfGoals);
        return true;
    }

    // public Referee setReferee(Referee referee) {
    // if (referee.CheckRefereeAvailability(this)) {
    // this.referee = referee;
    // return referee;
    // }

    // return null;
    // }

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

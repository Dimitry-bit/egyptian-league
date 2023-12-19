package com.github.egyptian_league;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import com.github.egyptian_league.json.Annotations.JsonConstructor;

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

    public Team getHomeTeam() {
        return ApplicationRepository.getRepository().getTeamById(homeTeamId);
    }

    public boolean setHomeTeam(UUID homeTeamId) {
        if (!ApplicationRepository.getRepository().containsTeamUUID(homeTeamId)) {
            return false;
        }

        this.homeTeamId = homeTeamId;
        return true;
    }

    public Team getAwayTeam() {
        return ApplicationRepository.getRepository().getTeamById(awayTeamId);
    }

    public boolean setAwayTeam(UUID awayTeamId) {
        if (!ApplicationRepository.getRepository().containsTeamUUID(awayTeamId)) {
            return false;
        }

        this.awayTeamId = awayTeamId;
        return true;
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
        ArrayList<Player> homeTeamPlayers = getHomeTeam().getPlayers();
        ArrayList<Player> awayTeamPlayers = getAwayTeam().getPlayers();

        for (Player p : homeTeamPlayers) {
            if (scorers.containsKey(p.Id)) {
                homeScore += scorers.get(p.Id);
            }
        }

        for (Player p : awayTeamPlayers) {
            if (scorers.containsKey(p.Id)) {
                awayScore += scorers.get(p.Id);
            }
        }

        if (homeScore == awayScore) {
            return null;
        }

        return (homeScore > awayScore) ? homeTeamId : awayTeamId;
    }
}

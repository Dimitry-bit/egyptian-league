package com.github.egyptian_league;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class Match {
    public final UUID matchId;
    public final UUID HomeTeamId;
    public final UUID AwayTeamId;
    private UUID matchStadium;
    public Referee Referee;
    private LocalDateTime dateTime;
    private HashMap<UUID, Integer> goalScorers;

    // FIXME: Should be calculated
    private UUID winnerTeam;

    public Match(UUID homeTeamId, UUID awayTeamId) {
        matchId = UUID.randomUUID();
        this.HomeTeamId = homeTeamId;
        this.AwayTeamId = awayTeamId;
    }

    public UUID getWinnerTeam() {
        return winnerTeam;
    }

    public UUID getMatchStadium() {
        return matchStadium;
    }

    public void setMatchStadium(UUID matchStadium) {
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

    // FIXME: Can I remove a goal? seems like a reasonable request
    public void addGoal(UUID playerId, int numOfGoals) {
        // FIXME: Validate numbOfGoals before setting
        goalScorers.put(playerId, numOfGoals);
    }

    public Referee setReferee(Referee referee) {
        if (referee.CheckRefereeAvailability(this)) {
            this.Referee = referee;
            return referee;
        }
        return null;
    }

    // FIXME: Should return winner team instead of void
    // FIXME: winner team shouldn't be cached
    public void setWinnerTeam() {
        int homeScore = 0;
        int awayScore = 0;

        // FIXME: USE A VARIABLE !!!!!!!
        for (int i = 0; i < ApplicationRepository.defaultRepository.getTeamById(HomeTeamId).getPlayers().size(); i++) {
            if (goalScorers
                    .containsKey(ApplicationRepository.defaultRepository.getTeamById(HomeTeamId).getPlayers().get(i))) {
                homeScore += goalScorers
                        .get(ApplicationRepository.defaultRepository.getTeamById(HomeTeamId).getPlayers().get(i));
            }
        }

        // FIXME: USE A VARIABLE !!!!!!!
        for (int i = 0; i < ApplicationRepository.defaultRepository.getTeamById(AwayTeamId).getPlayers().size(); i++) {
            if (goalScorers
                    .containsKey(ApplicationRepository.defaultRepository.getTeamById(AwayTeamId).getPlayers().get(i))) {
                awayScore += goalScorers
                        .get(ApplicationRepository.defaultRepository.getTeamById(AwayTeamId).getPlayers().get(i));
            }
        }

        if (homeScore > awayScore) {
            winnerTeam = HomeTeamId;
        } else if (homeScore < awayScore)
            winnerTeam = AwayTeamId;
        else
            winnerTeam = null;
    }

    // FIXME: Why?
    /*
     * public static Boolean isComingDate(LocalDateTime currentDate, LocalDateTime
     * suggestedDate) {
     * return currentDate.isAfter(suggestedDate);
     * }
     */
}

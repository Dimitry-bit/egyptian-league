package com.github.egyptian_league.POJOs;

import com.github.egyptian_league.Models.Match;
import com.github.egyptian_league.Models.Referee;
import com.github.egyptian_league.Models.Stadium;
import com.github.egyptian_league.Models.Team;

import java.time.LocalDateTime;

public class MatchPojo {

    private Match match;

    public MatchPojo(Match match) {
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }

    public String getHomeTeam() {
        Team homeTeam = match.getHomeTeam();
        return (homeTeam != null) ? homeTeam.getName() : "";
    }

    public String getAwayTeam() {
        Team awayTeam = match.getAwayTeam();
        return (awayTeam != null) ? awayTeam.getName() : "";
    }

    public String getStadium() {
        Stadium stadium = match.getStadium();
        return (stadium != null) ? stadium.getName() : "";
    }

    public String getReferee() {
        Referee referee = match.getReferee();
        return (referee != null) ? referee.getName() : "";
    }

    public LocalDateTime getDate() {
        return match.getDateTime();
    }

    public String getWinner() {
        Team winnerTeam = match.calcWinnerTeam();
        if (winnerTeam == null) {
            return "Tie";
        }

        return winnerTeam.getName();
    }
}
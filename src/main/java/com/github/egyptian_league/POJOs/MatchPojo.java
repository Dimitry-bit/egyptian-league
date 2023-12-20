package com.github.egyptian_league.POJOs;

import com.github.egyptian_league.Models.Match;
import com.github.egyptian_league.Models.Team;

import java.time.LocalDateTime;

public class MatchPojo {

    Match match;

    public MatchPojo(Match match) {
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }

    public String getHomeTeam() {
        return match.getHomeTeam().getName();
    }

    public String getAwayTeam() {
        return match.getAwayTeam().getName();
    }

    public String getStadium() {
        return match.getStadium().getName();
    }

    public String getReferee() {
        return match.getReferee().getName();
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
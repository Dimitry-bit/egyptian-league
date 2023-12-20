package com.github.egyptian_league.POJOs;

import com.github.egyptian_league.Models.Match;
import com.github.egyptian_league.Models.Referee;
import com.github.egyptian_league.Models.Stadium;
import com.github.egyptian_league.Models.Team;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class MatchPojo {
    Team homeTeam;
    Team awayTeam;
    Stadium stadium;
    Referee referee;
    LocalDateTime date;
    HashMap<UUID, Integer> score;

    public MatchPojo(Match match) {

        homeTeam = match.getHomeTeam();
        awayTeam = match.getAwayTeam();
        stadium = match.getStadium();
        referee = match.getReferee();
        date = match.getDateTime();
        score=match.getScorers();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Stadium getStadium() {
        return stadium;
    }
    public Referee getReferee() {
        return referee;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public HashMap<UUID, Integer> getScore() {
        return score;
    }
}
package com.github.egyptian_league.POJOs;

import com.github.egyptian_league.Models.Player;
import com.github.egyptian_league.Models.Team;

public class TeamPojo {

    private Team team;

    public TeamPojo(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public String getTeamName() {
        return team.getName();
    }

    public String getTeamCaptain() {
        Player captain = team.getCaptain();
        return (captain != null) ? captain.getName() : "";
    }

    public Integer getTotalScore() {
        return team.calcTotalPoints();
    }
}
package com.github.egyptian_league;

import com.github.egyptian_league.Models.Team;

public class TeamPojo {

    private Team team;
    private String TeamName;
    private String TeamCaptain;
    private Integer TotalScore;

    public TeamPojo(Team team) {
        this.team = team;

        TeamName = team.getName();
        TeamCaptain = "";
        TotalScore = team.calcTotalPoints();

        if (team.getCaptain() != null) {
            TeamCaptain = team.getCaptain().getName();
        }
    }

    public Team getTeam() {
        return  team;
    }

    public void setTeamCaptain(String teamCaptain) {
        TeamCaptain = teamCaptain;
    }

    public void setTotalScore(Integer totalScore) {
        TotalScore = totalScore;
    }

    public String getTeamName() {
        return TeamName;
    }

    public String getTeamCaptain() {
        return TeamCaptain;
    }

    public Integer getTotalScore() {
        return TotalScore;
    }
}
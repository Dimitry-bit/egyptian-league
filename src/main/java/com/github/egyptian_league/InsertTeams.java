package com.github.egyptian_league;

public class InsertTeams {
    private String TeamName;
    private Integer TeamId;

    public InsertTeams(String teamName, Integer teamId) {
        TeamName = teamName;
        TeamId = teamId;
    }

    public String getTeamName() {
        return TeamName;
    }

    public int getTeamId() {
        return TeamId;
    }
}

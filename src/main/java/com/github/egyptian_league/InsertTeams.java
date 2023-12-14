package com.github.egyptian_league;

public class InsertTeams {
    private String TeamName;
    private Integer TeamId;

    private String TeamCaptain;
    private Integer TotalScore;

    public InsertTeams(String teamName, Integer teamId, String teamCaptain, Integer totalScore) {
        TeamName = teamName;
        TeamId = teamId;
        TeamCaptain = teamCaptain;
        TotalScore = totalScore;
    }

    public String getTeamName() {
        return TeamName;
    }

    public Integer getTeamId() {
        return TeamId;
    }

    public String getTeamCaptain() {
        return TeamCaptain;
    }

    public Integer getTotalScore() {
        return TotalScore;
    }
}
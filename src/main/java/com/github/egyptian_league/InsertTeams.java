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

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public void setTeamId(Integer teamId) {
        TeamId = teamId;
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
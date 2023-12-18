package com.github.egyptian_league;

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

        if (ApplicationRepository.getRepository().containsPlayerUUID(team.getCaptain())) {
            TeamCaptain = ApplicationRepository.getRepository().getPlayerByUUID(team.getCaptain()).getName();
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
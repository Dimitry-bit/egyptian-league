package com.github.egyptian_league;

import java.util.Date;

public class Match {
   private int matchID;
   private Date date;
    private Team team1;
    private Team team2;
    private String stadiumName;
    private Score score;
    private Referee referee;

    public Match(int matchID, Date date, Team team1, Team team2, String stadiumName, Score score, Referee referee) {
        this.matchID = matchID;
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.stadiumName = stadiumName;
        this.score = score;
        this.referee = referee;
    }
    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

}

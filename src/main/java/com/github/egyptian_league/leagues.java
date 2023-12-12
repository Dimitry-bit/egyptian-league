package com.github.egyptian_league;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class leagues {
    public final String league_Name;
    private final String name;
    private final int year;
    private final ArrayList<Team> teams = new ArrayList<>();
    private final ArrayList<player> Players = new ArrayList<>();

    private final ArrayList<Match> Matches = new ArrayList<Match>();

    public leagues(String name, int year) throws sye {
        this.name = name;
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        if (year < currentYear) {

            try {
                throw new sye(year, currentYear);
            } catch (sye e) {
                throw new RuntimeException(e);
            }
        } else {
            this.year = year;
        }
        league_Name = name + " " + year;
    }


    public void getplayers() {
            int i = 0;
        for (player x : Players) {
            System.out.println(Players.get(i).getId());
            System.out.println(Players.get(i).getAge());
            System.out.println(Players.get(i).getGoalScored());
            System.out.println(Players.get(i).getPosition());
            System.out.println(Players.get(i).getName());
            System.out.println(Players.get(i).getTeam());
            System.out.println(Players.get(i).getNumber());
            System.out.println(Players.get(i).getRank());
            i++;
        }
    }


    public void getteams() {
            int i = 0;
        for (Team x : teams) {
            System.out.println(teams.get(i).getTotal_Score());

//            System.out.println(teams.get(0).getCaptain());[error i dont know y]
            System.out.println(teams.get(i).getTeam_ID());
            System.out.println(teams.get(i).getName());

            i++;
        }
    }

    public void setMatches(int matchID, Date date, Team team1, Team team2, String stadiumName, Score score, Referee referee) {
        Matches.add(new Match(matchID, date, team1, team2, stadiumName, score, referee));
    }

    public void getmatch() {
            int i = 0;
        for (Match x : Matches) {
            System.out.println(Matches.get(i).getMatchID());
            System.out.println(Matches.get(i).getStadiumName());
            System.out.println(Matches.get(i).getDate());
            System.out.println(Matches.get(i).getReferee());
            System.out.println(Matches.get(i).getScore());
            System.out.println(Matches.get(i).getTeam1());
            System.out.println(Matches.get(i).getTeam2());
            i++;
        }
    }
    public void setPlayers(String name, String team, String position, int id, int number, int age, int GoalScored, int Rank) {
        Players.add(new player(name, team, position, id, number, age, GoalScored, Rank));
    }
    public void setTeams(String Name, int Team_ID, int Total_Score, player Captain) {
        teams.add(new Team(name, Team_ID, Total_Score, Captain));
    }
}
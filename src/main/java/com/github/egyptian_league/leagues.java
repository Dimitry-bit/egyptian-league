package com.github.egyptian_league;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class leagues {
    public final String league_Name;
    private final String name;
    private final int year;
    private final ArrayList<Team> teams = new ArrayList<>();
    private final ArrayList<Player> Players = new ArrayList<>();

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

    public void setMatches(int matchID, Date date, Team team1, Team team2, String stadiumName, Score score,
            Referee referee) {
        Matches.add(new Match(matchID, date, team1, team2, stadiumName, score, referee));
    }

    // public void setPlayers(String name, String team, String position, int id, int number, int age, int GoalScored,
    //         int Rank) {
    //     Players.add(new player(name, team, position, id, number, age, GoalScored, Rank));
    // }

    public void setTeams(String Name, int Team_ID, int Total_Score, Player Captain) {
        teams.add(new Team(name, Team_ID, Total_Score, Captain));
    }
}

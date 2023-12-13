package com.github.egyptian_league;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class League {

    // FIXME: name, and leagueName does not make any sense
    public final String LeagueName;
    private final String name;

    // FIXME: Datatype should be LocalDate?
    private final int year;

    // FIXME: Switch to using UUIDs instead
    // FIXME: teams, player, and matches could and will change through the lifetime
    // of the program, so they shouldn't be final
    private final ArrayList<Team> teams = new ArrayList<>();
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Match> matches = new ArrayList<>();

    // FIXME: Does it really have to throw a checked exception?
    public League(String name, int year) throws InvalidYearException {
        this.name = name;
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        // FIXME: Move to the beginning of the current block
        if (year < currentYear) {
            try {
                throw new InvalidYearException(year, currentYear);
            } catch (InvalidYearException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.year = year;
        }
        LeagueName = name + " " + year;
    }

    // FIXME: Use an object instead of multiple parameters
    // public void setMatches(int matchID, Date date, Team team1, Team team2, String stadiumName, Score score,
    //         Referee referee) {
    //     matches.add(new Match(matchID, date, team1, team2, stadiumName, score, referee));
    // }

    // FIXME: Use an object instead of multiple parameters
    // public void setPlayers(String name, String team, String position, int id, int
    // number, int age, int GoalScored,
    // int Rank) {
    // Players.add(new player(name, team, position, id, number, age, GoalScored,
    // Rank));
    // }

    // FIXME: Use an object instead of multiple parameters
    public void setTeams(String Name, int Team_ID, int Total_Score, Player Captain) {
        teams.add(new Team(name, Team_ID, Total_Score, Captain));
    }
}

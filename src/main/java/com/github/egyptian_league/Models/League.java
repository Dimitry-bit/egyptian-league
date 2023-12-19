package com.github.egyptian_league.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import com.github.egyptian_league.ApplicationRepository;

public class League {

    public final UUID Id;

    private String LeagueName;
    private int year;
    private final ArrayList<UUID> teams = new ArrayList<>();
    private final ArrayList<UUID> matches = new ArrayList<>();

    public League(String LeagueName, int year) {
        setYear(year);
        this.Id = UUID.randomUUID();
        this.LeagueName = LeagueName;
    }

    public String getLeagueName() {
        return LeagueName;
    }

    public void setLeagueName(String leagueName) {
        LeagueName = leagueName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        int Year = LocalDate.now().getYear();

        if (year < Year) {
            throw new InvalidYearException("the year " + year + " is less than current year " + Year);
        }

        this.year = year;
    }

    public ArrayList<Match> getMatches() {
        ArrayList<Match> matches = new ArrayList<>();

        for (UUID matchId : this.matches) {
            Match match = ApplicationRepository.getRepository().getMatchByUUID(matchId);
            matches.add(match);
        }

        return matches;
    }

    public void addMatches(UUID matchId) {
        matches.add(matchId);
    }

    public void deleteMatch(UUID matchId) {
        matches.remove(matchId);
    }

    public ArrayList<Team> getTeams() {
        ArrayList<Team> teams = new ArrayList<>();

        for (UUID teamId : this.teams) {
            Team team = ApplicationRepository.getRepository().getTeamById(teamId);
            teams.add(team);
        }

        return teams;
    }

    public void addTeams(UUID teamID) {
        teams.add(teamID);
    }

    public void deleteTeam(UUID teamID) {
        teams.remove(teamID);
    }
}

package com.github.egyptian_league;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class League {

    private  String LeagueName;

    private int year;



    private final ArrayList<UUID> teams = new ArrayList<>();
//    private final ArrayList<UUID> players = new ArrayList<>();
    private final ArrayList<UUID> matches = new ArrayList<>();

    public League(String LeagueName, int year) {
        setYear(year);
        this.LeagueName = LeagueName;
    }

    public void addMatches(UUID matchId) {
        matches.add(matchId);
    }

//    public void addPlayers(UUID playerId) {
//        players.add(playerId);
//    }

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

    public void addTeams(UUID teamID) {
        teams.add(teamID);
    }

//    public void deletePlayer(UUID playerId) {
//        players.remove(playerId);
//    }

    public void setLeagueName(String leagueName) {
        LeagueName = leagueName;
    }

    public String getLeagueName() {
        return LeagueName;
    }

    public void deleteMatch(UUID matchId) {
        matches.remove(matchId);
    }

    public void deleteTeam(UUID teamID) {
        teams.remove(teamID);
    }
    public Iterator<UUID> getTeams(){
        return teams.iterator();
    }

    public ArrayList<UUID> getPlayers() {
        ArrayList<UUID> players = new ArrayList<>();
        for (UUID team : teams) {
            Team obj = ApplicationRepository.getRepository().getTeamById(team);
            Iterator<UUID> iterator = obj.getPlayers();
            while (iterator.hasNext()) {
                players.add(iterator.next());
            }
        }
        return players;
    }

        public Iterator<UUID> getMatches () {
            return matches.iterator();
        }
    }
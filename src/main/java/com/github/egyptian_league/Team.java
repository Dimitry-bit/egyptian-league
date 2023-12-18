package com.github.egyptian_league;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import com.github.egyptian_league.json.Annotations.JsonConstructor;

public class Team {

    public final UUID ID;

    private String name;
    private UUID captain;
    private ArrayList<UUID> players = new ArrayList<>();

    @JsonConstructor(parameters = { "name", "captainId" })
    public Team(String name, UUID captainId) {
        this.ID = UUID.randomUUID();
        this.name = name;
        this.captain = captainId;
    }

    public static int calcNumberOfTeams() {
        return ApplicationRepository.getRepository().getNumberOfTeams();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getCaptain() {
        return ApplicationRepository.getRepository().getPlayerByUUID(captain);
    }

    public void setCaptain(UUID captain) {
        this.captain = captain;
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();

        for (UUID playerUUID : this.players) {
            if (!ApplicationRepository.getRepository().containsPlayerUUID(playerUUID)) {
                continue;
            }

            Player player = ApplicationRepository.getRepository().getPlayerByUUID(playerUUID);
            players.add(player);
        }

        return players;
    }

    public boolean containsPlayer(UUID playerId) {
        return players.contains(playerId);
    }

    public boolean addPlayer(UUID playerId) {
        return players.add(playerId);
    }

    public boolean removePlayer(UUID playerId) {
        return players.remove(playerId);
    }

    public int calcTotalPoints() {
        int totalPoints = 0;
        Iterator<Match> matchesIterator = ApplicationRepository.getRepository().getMatchesIterator();

        while (matchesIterator.hasNext()) {
            Match match = matchesIterator.next();
            if (match.calcWinnerTeam().equals(ID)) {
                totalPoints += 3;
            } else if (match.calcWinnerTeam().equals(null)) {
                totalPoints += 1;
            }
        }

        return totalPoints;
    }
}

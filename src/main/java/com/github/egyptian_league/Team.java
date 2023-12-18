package com.github.egyptian_league;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import com.github.egyptian_league.json.Annotations.JsonConstructor;

public class Team {

    private final String name;
    private final UUID teamID;
    private UUID captain;
    private ArrayList<UUID> players = new ArrayList<>();

    @JsonConstructor(parameters = { "name", "captain" })
    public Team(String name, UUID captain) {
        this.name = name;
        this.teamID = UUID.randomUUID();
        this.captain = captain;
    }

    public String getName() {
        return name;
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

    public UUID getUuid() {
        return teamID;
    }

    public Player getCaptain() {
        return ApplicationRepository.getRepository().getPlayerByUUID(captain);
    }

    public void setCaptain(UUID captain) {
        this.captain = captain;
    }

    public int calcTotalPoints() {
        int totalPoints = 0;
        Iterator<Match> matchsIterator = ApplicationRepository.getRepository().getMatchesIterator();
        while (matchsIterator.hasNext()) {
            Match match = matchsIterator.next();
            if (match.calcWinnerTeam().equals(teamID)) {
                totalPoints += 3;
            } else if (match.calcWinnerTeam().equals(null)) {
                totalPoints += 1;
            }

        }
        return totalPoints;

    }

    public static int calcNumberOfTeams() {
        return ApplicationRepository.getRepository().getNumberOfTeams();
    }

    private boolean containsPlayer(UUID playerId) {
        return players.contains(playerId);
    }

    public boolean addPlayer(UUID playerId) {
        return players.add(playerId);
    }

    public boolean removePlayer(UUID playerId) {
        return players.remove(playerId);
    }
}

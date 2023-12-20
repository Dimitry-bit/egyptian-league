package com.github.egyptian_league.Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Json.Annotations.JsonConstructor;

public class Team {

    public final UUID Id;

    private String name;
    private UUID captainId;
    private ArrayList<UUID> players = new ArrayList<>();

    @JsonConstructor(parameters = { "name", "captainId" })
    public Team(String name, UUID captainId) {
        this.Id = UUID.randomUUID();
        this.name = name;
        this.captainId = captainId;
    }

    public void delete() {
        Player captain = getCaptain();
        ArrayList<Player> players = getPlayers();

        if (captain != null) {
            captain.setTeam(null);
        }

        for (Player p : players) {
            p.setTeam(null);
        }

        Iterator<Match> matchesIterator = ApplicationRepository.getRepository().getMatchesIterator();
        ArrayList<Match> markedForRemoval = new ArrayList<>();
        while (matchesIterator.hasNext()) {
            Match match = matchesIterator.next();
            if (match.containsTeam(this)) {
                markedForRemoval.add(match);
            }
        }

        for (Match m : markedForRemoval) {
            ApplicationRepository.getRepository().removeMatch(m);
        }
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
        return ApplicationRepository.getRepository().getPlayerByUUID(captainId);
    }

    public boolean setCaptain(Player captain) {
        if (captain == null) {
            this.captainId = null;
            return true;
        }

        if (!ApplicationRepository.getRepository().containsPlayer(captain)) {
            return false;
        }

        this.captainId = captain.Id;
        return true;
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

            if (!match.containsTeam(this)) {
                continue;
            }

            Team winnerTeam = match.calcWinnerTeam();

            // NOTE: equals() returns false if obj is null, so null check must be done first
            if (winnerTeam == null) {
                totalPoints += 1;
            } else if (winnerTeam.Id.equals(Id)) {
                totalPoints += 3;
            }
        }

        return totalPoints;
    }
}

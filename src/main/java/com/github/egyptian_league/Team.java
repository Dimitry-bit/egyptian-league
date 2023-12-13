package com.github.egyptian_league;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class Team {

    // FIXME: Where does it get decremented? Can it be calculated?
    private final String name;
    private final UUID teamID;

    // FIXME: Shouldn't it be calculated?
    private int totalPoints;

    private UUID captain;
    private ArrayList<UUID> players = new ArrayList<>();

    public ArrayList<UUID> getPlayers() {
        return players;
    }

    public Team(String name, UUID captain) {
        this.name = name;
        this.teamID = UUID.randomUUID();
        // FIXME: Why are you generating random UUID for captain? Do you own it? Is it a part of Team?
        this.captain = captain;
        totalPoints = 0;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return teamID;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public UUID getCaptain() {
        return captain;
    }

    public void setCaptain(UUID captain) {
        this.captain = captain;
    }

    public void calcTotalPoints(Match match) {
        if (teamID.equals(match.getWinnerTeam())) {
            totalPoints += 3;
        } else if (match.getWinnerTeam().equals(null)) {
            totalPoints += 1;
        }
    }

    // FIXME: Bad solution and most likely will get missed

    // FIXME: Should ACTUALLY calculate number of teams !!
    public static int calcNumberOfTeams() {
        return ApplicationRepository.defaultRepository.getNumberOfTeams();
    }

    private boolean IsExistPlayer(UUID playerId) {
        return players.contains(playerId);
    }

    public boolean AddPlayer(UUID playerId) {
        return players.add(playerId);
    }

    public boolean RemovePlayer(UUID playerId) {
        return players.remove(playerId);
    }
}

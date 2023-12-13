package com.github.egyptian_league;

import java.util.ArrayList;
import java.util.UUID;

public class Team {

    // FIXME: Where does it get decremented? Can it be calculated?
    private static int numberOfTeams = 0;

    private final String name;
    private final UUID teamID;

    // FIXME: Shouldn't it be calculated?
    private int totalPoints;

    private UUID captain;
    private ArrayList<UUID> players = new ArrayList<>();

    public Team(String name, UUID captain) {
        this.name = name;
        this.teamID = UUID.randomUUID();

        // FIXME: Why are you generating random UUID for captain? Do you own it? Is it a part of Team?
        this.captain = UUID.randomUUID();
        numberOfTeams++;
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

    // FIXME: Bad solution and most likely will get missed
    public static void DeleteTeam(UUID TeamId) {
        numberOfTeams--;
    }

    // FIXME: Should ACTUALLY calculate number of teams !!
    public int calcNumberOfTeams() {
        return numberOfTeams;
    }

    // NOTE: Could simplify expression
    // return player.contains(playerId);
    private boolean IsExistPlayer(UUID playerId) {
        if (players.contains(playerId)) {
            return true;
        } else {
            return false;
        }
    }

    // NOTE: What happens if playerId does exist? How should I know if playerId did in fact get added to the list?
    public void AddPlayer(UUID playerId) {
        if (!IsExistPlayer(playerId)) {
            players.add(playerId);
        }
    }

    public void RemovePlayer(UUID playerId) {
        if (IsExistPlayer(playerId)) {
            players.remove(playerId);
        }
    }
}

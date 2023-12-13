package com.github.egyptian_league;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class Team {

    // FIXME: Where does it get decremented? Can it be calculated?
    private static int numberOfTeams = 0;

    // FIXME: Why isn't it final? Does it change?
    private final String name;
    private final UUID teamID;

    // FIXME: Shouldn't it be calculated?
    private int totalPoints;

    // FIXME: Switch to UUIDs
    private UUID captain;

    // FIXME: Switch to UUIDs instead
    private ArrayList<UUID> players = new ArrayList<>();

    public Team(String name, UUID captain) {
        this.name = name;
        this.teamID = UUID.randomUUID();
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

    // FIXME: Should ACTUALLY calculate number of teams !!
    public static void DeleteTeam(UUID TeamId)
    {
        numberOfTeams--;
    }

    public int calcNumberOfTeams() {
        return numberOfTeams;
    }

    // FIXME: Research ArrayList (Lecture 7)
    private boolean IsExistPlayer(UUID playerId) {
        if (players.contains(playerId)) {
            return true;
        } else {
            return false;
        }
    }
    public void AddPlayer(UUID playerId) {
        if (!IsExistPlayer(playerId)) {
            players.add(playerId);
        }
    }

    // FIXME: Research ArrayList (Lecture 7)
    public void RemovePlayer(UUID playerId) {
         if (IsExistPlayer(playerId))
         {
             players.remove(playerId);
         }
    }
}

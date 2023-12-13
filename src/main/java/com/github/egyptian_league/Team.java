package com.github.egyptian_league;

import java.util.ArrayList;

public class Team {

    // FIXME: Where does it get decremented? Can it be calculated?
    private static int numberOfTeams = 0;

    // FIXME: Why isn't it final? Does it change?
    private String name;
    private int uuid;

    // FIXME: Shouldn't it be calculated?
    private int totalScore;

    // FIXME: Switch to UUIDs
    private Player captain;

    // FIXME: Switch to UUIDs instead
    private ArrayList<Player> players = new ArrayList<>();

    public Team(String name, int teamId, int totalScore, Player captain) {
        this.name = name;
        this.uuid = teamId;
        this.totalScore = totalScore;
        this.captain = captain;
        numberOfTeams++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUuid() {
        return uuid;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public Player getCaptain() {
        return captain;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    // FIXME: Should ACTUALLY calculate number of teams !!
    public int calcNumberOfTeams() {
        return numberOfTeams;
    }

    // FIXME: Research ArrayList (Lecture 7)
    public void addPlayer(Player Input_player) {
        // for (int i = 0; i < players.size(); i++) {
        //     if (players.get(i) == null) {
        //         players.set(i, Input_player);
        //         return;
        //     }

        // }
        // players.add(Input_player);
    }

    // FIXME: Research ArrayList (Lecture 7)
    public void removePlayer(String Player_Name, int Player_ID) {
        // for (int i = 0; i < players.size(); i++) {
        //     if (players.get(i) != null && players.get(i).getName().equals(Player_Name)
        //             && players.get(i).getPlayer_Id() == Player_ID) {
        //         players.remove(i);
        //         break;
        //     }
        // }
    }
}

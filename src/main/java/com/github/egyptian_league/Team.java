package com.github.egyptian_league;

import java.util.ArrayList;

public class Team {
    private String Name;
    private int Team_ID;
    private int Total_Score;
    private Player Captain;
    private static int Num_of_Teams = 0;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Match> Matches = new ArrayList<Match>();

    public Team(String Name, int Team_ID, int Total_Score, Player Captain) {
        this.Name = Name;
        this.Team_ID = Team_ID;
        this.Total_Score = Total_Score;
        this.Captain = Captain;
        Num_of_Teams++;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getTeam_ID() {
        return Team_ID;
    }

    public void setTeam_ID(int team_ID) {
        Team_ID = team_ID;
    }

    public int getTotal_Score() {
        return Total_Score;
    }

    public void setTotal_Score(int total_Score) {
        Total_Score = total_Score;
    }

    public Player getCaptain() {
        return Captain;
    }

    public void setCaptain(Player captain) {
        Captain = captain;
    }

    public int Calculate_numberOfTeams() {
        return Num_of_Teams;
    }

    public void ADD_Player(Player Input_player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) == null) {
                players.set(i, Input_player);
                return;
            }

        }
        players.add(Input_player);
    }

    public void Delete_Player(String Player_Name, int Player_ID) {
        // for (int i = 0; i < players.size(); i++) {
        //     if (players.get(i) != null && players.get(i).getName().equals(Player_Name) && players.get(i).getId() == Player_ID) {
        //         players.remove(i);
        //         break;
        //     }
        // }
    }

}

package com.github.egyptian_league.POJOs;

import java.time.LocalDate;

import com.github.egyptian_league.Models.Player;
import com.github.egyptian_league.Models.Position;

public class PlayerPojo {

    private Player player;

    public PlayerPojo(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return player.getName();
    }

    public String getTeamName() {
        return (player.getTeam() != null) ? player.getTeam().getName() : "";
    }

    public LocalDate getBirthday() {
        return player.getBirthday();
    }

    public Position getPosition() {
        return player.getPosition();
    }

    public Integer getShirtNumber() {
        return player.getShirtNumber();
    }

    public Integer getAge() {
        return player.calcAge();
    }

    public Integer getRank() {
        return player.calcRank();
    }
}

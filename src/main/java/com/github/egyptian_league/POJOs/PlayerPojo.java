package com.github.egyptian_league.POJOs;

import java.time.LocalDate;

import com.github.egyptian_league.Models.Player;
import com.github.egyptian_league.Models.Position;

public class PlayerPojo {
    private final String name;
    private final String teamName;
    private final LocalDate birthday;
    private final Position position;
    private final Integer shirtNumber;
    private final Integer age;
    private final Integer rank;

    public PlayerPojo(Player player) {
        name = player.getName();
        teamName = (player.getTeam() != null) ? player.getTeam().getName() : "";
        birthday = player.getBirthday();
        position = player.getPosition();
        shirtNumber = player.getShirtNumber();

        age = player.calcAge();
        rank = player.calcRank();
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Position getPosition() {
        return position;
    }

    public Integer getShirtNumber() {
        return shirtNumber;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getRank() {
        return rank;
    }
}

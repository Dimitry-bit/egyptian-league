package com.github.egyptian_league;

public class PlayerPojo {
    private final String name;
    private final String teamName;
    private final Position position;
    private final Integer shirtNumber;
    private final Integer age;
    private final Integer rank;

    public PlayerPojo(Player player) {
        name = player.getName();
        position = player.getPosition();
        shirtNumber = player.getShirtNumber();
        age = player.calcAge();
        rank = player.calcRank();
        teamName = (player.getTeam() != null) ? player.getTeam().getName() : "";
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
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

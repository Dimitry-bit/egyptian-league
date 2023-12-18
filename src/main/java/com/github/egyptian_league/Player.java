package com.github.egyptian_league;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import com.github.egyptian_league.json.Annotations.JsonConstructor;

public class Player {

    private final UUID playerId;
    private final String name;
    private UUID team;
    private Position position;
    private int shirtNumber;

    // FIXME: Does it change?
    private LocalDate birthday;

    // FIXME: Can it be calculated?
    private int Rank;

    @JsonConstructor(parameters = { "name", "team", "position", "shirtNumber" })
    public Player(String name, UUID team, Position position, int number) {
        this.playerId = UUID.randomUUID();
        this.name = name;
        this.team = team;
        this.position = position;
        this.shirtNumber = number;
    }

    public void setTeam(UUID team) {
        this.team = team;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public UUID getId() {
        return playerId;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public UUID getTeam() {
        return team;
    }

    // FIXME: Actually calculate rank
    public int calcRank() {
        return Rank;
    }

    public int calcAge() {
        if (birthday == null) {
            return -1;
        }

        return Period.between(birthday, LocalDate.now()).getYears();
    }
}

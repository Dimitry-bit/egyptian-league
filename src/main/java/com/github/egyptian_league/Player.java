package com.github.egyptian_league;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

public class Player {

    private final UUID playerId;
    private final String name;
    private UUID team;
    private Position position;
    private int shirtNumber;

    // FIXME: Is time really that important?
    // FIXME: Does it change?
    private LocalDateTime birthday;

    // FIXME: Can it be calculated?
    private int Rank;

    public Player(String name, UUID team, Position position, int number) {
        this.playerId = UUID.randomUUID();
        this.name = name;
        this.team = UUID.randomUUID();
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



    public class AgeCalculator {
        public static int getAge(LocalDate dateOfBirth) {
            LocalDate today = LocalDate.now();

            int age = Period.between(dateOfBirth, today).getYears();

            return age;
        }


    }
}

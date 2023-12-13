package com.github.egyptian_league;

import java.time.LocalDateTime;
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

    // public static int getAge(Date dateOfBirth) {

    // Calendar today = Calendar.getInstance();
    // Calendar birthDate = Calendar.getInstance();

    // int age = 0;

    // birthDate.setTime(dateOfBirth);

    // age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

    // // If birth date is greater than todays date (after 2 days adjustment of leap
    // // year) then decrement age one year
    // if ((birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) >
    // 3) ||
    // (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
    // age--;

    // // If birth date and todays date are of same month and birth day of month is
    // // greater than todays day of month then decrement age
    // } else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH)) &&
    // (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
    // age--;
    // }

    // return age;
    // }
}

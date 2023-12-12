package com.github.egyptian_league;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.UUID;

public class player {
    final private String name;
    private String team;

    position position;
    UUID uuid;
    private int number;
    // private Date birthday;

    private int Rank;

    public player(String name, String team, position position, int number) {

        this.name = name;
        this.team = team;
        this.position = position;
        this.number = number;

    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPosition(position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public position getPosition() {
        return position;
    }

    public UUID getId() {
        return uuid;
    }

    public int getNumber() {
        return number;
    }

    public String getTeam() {
        return team;
    }

    // public static int getAge(Date dateOfBirth) {

    //     Calendar today = Calendar.getInstance();
    //     Calendar birthDate = Calendar.getInstance();

    //     int age = 0;

    //     birthDate.setTime(dateOfBirth);

    //     age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

    //     // If birth date is greater than todays date (after 2 days adjustment of leap
    //     // year) then decrement age one year
    //     if ((birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
    //             (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
    //         age--;

    //         // If birth date and todays date are of same month and birth day of month is
    //         // greater than todays day of month then decrement age
    //     } else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH)) &&
    //             (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
    //         age--;
    //     }

    //     return age;
    // }
}

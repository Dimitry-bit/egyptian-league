package com.github.egyptian_league.POJOs;

import com.github.egyptian_league.Models.Stadium;

public class StadiumPojo {

    private Stadium stadium;

    public StadiumPojo(Stadium stadium) {
        this.stadium = stadium;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public String getName() {
        return stadium.getName();
    }

    public String getAddress() {
        return stadium.getAddress();
    }
}

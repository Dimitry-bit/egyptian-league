package com.github.egyptian_league.POJOs;

import com.github.egyptian_league.Models.Referee;

public class RefereePojo {

    private Referee referee;

    public RefereePojo(Referee referee) {
        this.referee = referee;
    }

    public Referee getReferee() {
        return referee;
    }

    public String getName() {
        return referee.getName();
    }
}

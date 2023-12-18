package com.github.egyptian_league;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import com.github.egyptian_league.json.Annotations.JsonConstructor;

import java.util.Iterator;

public class Stadium {

    public final String StadiumName;
    public final String StadiumAddress;

    // FIXME: Where can I access it?
    private final UUID stadiumId;

    private ArrayList<LocalDate> stadiumSchedule = new ArrayList<>();

    @JsonConstructor(parameters = { "StadiumName", "StadiumAddress" })
    public Stadium(String StadiumName, String StadiumAddress) {
        this.StadiumName = StadiumName;
        this.StadiumAddress = StadiumAddress;
        stadiumId = UUID.randomUUID();
    }

    public boolean checkStadiumAvailability(LocalDate date) {
        for (LocalDate reservedDate : stadiumSchedule) {
            if (date.equals(reservedDate)) {
                return false;
            }
        }

        return true;
    }
}

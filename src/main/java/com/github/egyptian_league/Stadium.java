package com.github.egyptian_league;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import com.github.egyptian_league.json.Annotations.JsonConstructor;

public class Stadium {

    private final UUID stadiumId;
    private String stadiumName;
    private String stadiumAddress;
    private ArrayList<LocalDateTime> stadiumSchedule = new ArrayList<>();

    @JsonConstructor(parameters = { "StadiumName", "StadiumAddress" })
    public Stadium(String StadiumName, String StadiumAddress) {
        this.stadiumName = StadiumName;
        this.stadiumAddress = StadiumAddress;
        stadiumId = UUID.randomUUID();
    }

    public UUID getStadiumUUID() {
        return stadiumId;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public String getStadiumAddress() {
        return stadiumAddress;
    }

    public void setStadiumAddress(String stadiumAddress) {
        this.stadiumAddress = stadiumAddress;
    }

    public boolean checkStadiumAvailability(LocalDateTime date) {
        for (LocalDateTime reservedDate : stadiumSchedule) {
            if (date.equals(reservedDate) || date.isBefore(reservedDate.plusMinutes(90))) {
                return false;
            }
        }

        return true;
    }
}

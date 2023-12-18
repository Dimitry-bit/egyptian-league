package com.github.egyptian_league;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import com.github.egyptian_league.json.Annotations.JsonConstructor;

public class Stadium {

    public final UUID id;
    private String name;
    private String address;
    private ArrayList<LocalDateTime> schedule = new ArrayList<>();

    @JsonConstructor(parameters = { "name", "address" })
    public Stadium(String name, String address) {
        id = UUID.randomUUID();
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String stadiumName) {
        this.name = stadiumName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String stadiumAddress) {
        this.address = stadiumAddress;
    }

    public boolean checkStadiumAvailability(LocalDateTime date) {
        for (LocalDateTime reservedDate : schedule) {
            if (date.equals(reservedDate) || date.isBefore(reservedDate.plusMinutes(90))) {
                return false;
            }
        }

        return true;
    }
}

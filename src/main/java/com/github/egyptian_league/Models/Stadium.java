package com.github.egyptian_league.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import com.github.egyptian_league.Json.Annotations.JsonConstructor;

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

    public Iterator<LocalDateTime> getSchedule() {
        return schedule.iterator();
    }

    public void addDateTimeToSchedule(LocalDateTime dateTime) {
        schedule.add(dateTime);
    }

    public void removeDateTimeFromSchedule(LocalDateTime dateTime) {
        schedule.removeIf(dt -> dt.equals(dateTime));
    }

    public boolean checkStadiumAvailability(LocalDateTime date) {
        for (LocalDateTime reservedDate : schedule) {
            if (date.equals(reservedDate) || (date.isAfter(reservedDate) && date.isBefore(reservedDate.plusMinutes(90)))) {
                return false;
            }
        }
        return true;
    }
}

package com.github.egyptian_league.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

import com.github.egyptian_league.json.Annotations.JsonConstructor;

public class Referee {
    private final String name;
    private final UUID refereeId;
    private ArrayList<LocalDate> refereeSchedule = new ArrayList<>();

    @JsonConstructor(parameters = { "name" })
    Referee(String name) {
        this.name = name;
        refereeId = UUID.randomUUID();
    }

    public boolean CheckRefereeAvailability(Match match) {
        for (int i = 0; i <  refereeSchedule.size(); i++)
            if (match.getDateTime().toLocalDate().equals(refereeSchedule.get(i))) {
                return false;
            }
        return true;
    }
}

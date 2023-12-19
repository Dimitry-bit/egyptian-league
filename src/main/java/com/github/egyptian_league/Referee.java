package com.github.egyptian_league;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

public class Referee {
    private final String name;
    private final UUID refereeId;
    private ArrayList<LocalDate> refereeSchedule = new ArrayList<>();

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
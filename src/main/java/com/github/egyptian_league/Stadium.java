package com.github.egyptian_league;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Iterator;

public class Stadium {

    public final String StadiumName;
    public final String StadiumAddress;

    // FIXME: Where can I access it?
    private final UUID stadiumId;

    private ArrayList<LocalDate> stadiumSchedule = new ArrayList<>();

    public Stadium(String StadiumName, String StadiumAddress) {
        this.StadiumName = StadiumName;
        this.StadiumAddress = StadiumAddress;
        stadiumId = UUID.randomUUID();
    }

    // FIXME: Do you have to take in the matchIterator?
    public boolean checkStadiumAvailability(Match match) {
        for (int i = 0; i <  stadiumSchedule.size(); i++)
            if (match.getDateTime().toLocalDate().equals(stadiumSchedule.get(i)) && match.getDateTime().toLocalDate().isBefore(LocalDate.now())) {
                return false;
            }
        return true;
    }

}

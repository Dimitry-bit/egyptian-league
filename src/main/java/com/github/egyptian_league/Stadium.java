package com.github.egyptian_league;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Iterator;

public class Stadium {

    public final String StadiumName;
    public final String StadiumAddress;

    // FIXME: Where can I access it?
    private final UUID stadiumId;

    public Stadium(String StadiumName, String StadiumAddress) {
        this.StadiumName = StadiumName;
        this.StadiumAddress = StadiumAddress;
        stadiumId = UUID.randomUUID();
    }

    // FIXME: Do you have to take in the matchIterator?
    public static boolean checkStadiumAvailability(LocalDateTime dateTime, Iterator<Match> matchIterator) {
        while (matchIterator.hasNext()) {
            // FIXME: Condition is will yeild false on
            if (dateTime.equals(matchIterator.next().getDateTime()) && dateTime.isBefore(LocalDateTime.now())) {
                return false;
            }
        }
        return true;
    }
}

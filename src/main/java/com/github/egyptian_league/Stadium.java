package com.github.egyptian_league;

import java.time.LocalDateTime;
import java.util.ArrayList;

// FIXME: How can I access Stadium data (name, address, uuid)?
// FIXME: Shouldn't a Stadium be a unique object? Where is the UUID?
public class Stadium {

    // FIXME: A stadium object should represents a single stadium
    private ArrayList<String> stadiumNames;

    // FIXME: Moved to Match.java
    protected LocalDateTime matchDate;

    public Stadium(ArrayList<String> stadiumNames) {
        this.stadiumNames = stadiumNames;
    }

    // FIXME: Loop over all matches for this stadium and determine if searchedDate is a valid datetime
    public boolean checkStadiumAvailability(LocalDateTime dateTime) {
        if (dateTime.isEqual(matchDate) && dateTime.isBefore(matchDate)) {
            return false;
        }
        return true;
    }
}

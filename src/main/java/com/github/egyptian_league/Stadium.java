package com.github.egyptian_league;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Iterator;


// FIXME: How can I access Stadium data (name, address, uuid)?
// FIXME: Shouldn't a Stadium be a unique object? Where is the UUID?
public class Stadium {

    public final String StadiumName;
    public final String StadiumAddress;
    private final UUID stadiumId;
    public Stadium(String StadiumName , String StadiumAddress)
    {
        this.StadiumName=StadiumName;
        this.StadiumAddress=StadiumAddress;
        stadiumId = UUID.randomUUID();
    }
    // FIXME: A stadium object should represents a single stadium
    //private ArrayList<String> stadiumNames;


    // FIXME: Moved to Match.java
   /*protected LocalDateTime matchDate;
  public Stadium(ArrayList<String> stadiumNames) {
       this.stadiumNames = stadiumNames;}*/

    // FIXME: Loop over all matches for this stadium and determine if searchedDate is a valid datetime
    public static boolean checkStadiumAvailability(LocalDateTime dateTime,Iterator<Match> matchIterator)
    {
        while (matchIterator.hasNext()){
            if (dateTime.equals(matchIterator.next().getDateTime())&&dateTime.isBefore(LocalDateTime.now())){
                return false;
            }
        }
        return true;
    }
}

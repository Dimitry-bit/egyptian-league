package com.github.egyptian_league.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Json.Annotations.JsonConstructor;

public class Referee {

    public final UUID Id;

    private String name;
    private ArrayList<LocalDate> schedule = new ArrayList<>();

    @JsonConstructor(parameters = { "name" })
    public Referee(String name) {
        Id = UUID.randomUUID();
        this.name = name;
    }

    public void delete() {
        Iterator<Match> matchesIterator = ApplicationRepository.getRepository().getMatchesIterator();

        while (matchesIterator.hasNext()) {
            Match match = matchesIterator.next();
            if (match.getReferee().Id.equals(Id)) {
                match.setReferee(null);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Iterator<LocalDate> getSchedule() {
        return schedule.iterator();
    }

    public void addDateToSchedule(LocalDate date) {
        schedule.add(date);
    }

    public void removeDateFromSchedule(LocalDate date) {
        schedule.removeIf(dt -> dt.equals(date));
    }

    public boolean CheckRefereeAvailability(LocalDate date) {
        for (int i = 0; i < schedule.size(); i++) {
            if (date.equals(schedule.get(i))) {
                return false;
            }
        }
        return true;
    }
}

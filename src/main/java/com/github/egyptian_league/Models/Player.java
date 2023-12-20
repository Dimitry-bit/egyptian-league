package com.github.egyptian_league.Models;

import java.time.LocalDate;
import java.time.Period;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.UUID;
import java.util.Map.Entry;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Json.Annotations.JsonConstructor;

public class Player {

    public final UUID Id;

    private String name;
    private UUID teamId;
    private LocalDate birthday;
    private Position position;
    private int shirtNumber;

    @JsonConstructor(parameters = { "name", "teamId", "birthday", "position", "shirtNumber" })
    public Player(String name, UUID teamId, LocalDate birthday, Position position, int shirtNumber) {
        this.Id = UUID.randomUUID();
        this.name = name;
        this.teamId = teamId;
        this.position = position;
        this.birthday = birthday;
        this.shirtNumber = shirtNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        if (teamId == null) {
            return null;
        }

        return ApplicationRepository.getRepository().getTeamById(teamId);
    }

    public boolean setTeamId(UUID teamId) {
        if (!ApplicationRepository.getRepository().containsTeamUUID(teamId)) {
            return false;
        }

        this.teamId = teamId;
        return true;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public boolean setShirtNumber(int shirtNumber) {
        if (shirtNumber <= 0) {
            return false;
        }

        this.shirtNumber = shirtNumber;
        return true;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthDay(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int calcRank() {
        Iterator<Match> matchesIterator = ApplicationRepository.getRepository().getMatchesIterator();
        Iterator<Player> players = ApplicationRepository.getRepository().getPlayersIterator();
        Hashtable<UUID, Integer> scorers = new Hashtable<>();

        while (players.hasNext()) {
            scorers.put(players.next().Id, 0);
        }

        while (matchesIterator.hasNext()) {
            Match match = matchesIterator.next();
            for (Entry<UUID, Integer> entry : match.getScorers().entrySet()) {
                Integer i = scorers.get(entry.getKey()) + entry.getValue();
                scorers.put(entry.getKey(), i);
            }
        }

        return scorers.get(Id);
    }

    public int calcAge() {
        if (birthday == null) {
            return 0;
        }

        return Period.between(birthday, LocalDate.now()).getYears();
    }
}

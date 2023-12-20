package com.github.egyptian_league.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.github.egyptian_league.ApplicationRepository;

public class Match {

    public final UUID Id;

    private UUID homeTeamId;
    private UUID awayTeamId;
    private UUID stadiumId;
    private UUID refereeId;
    private LocalDateTime dateTime;
    private HashMap<UUID, Integer> scorers;

    // Needed for Json construction
    private Match() {
        Id = null;
    }

    public Match(UUID homeTeamId, UUID awayTeamId, UUID stadiumId, UUID refereeId, LocalDateTime dateTime) {
        this.Id = UUID.randomUUID();
        this.dateTime = dateTime;
        scorers = new HashMap<>();

        if (homeTeamId == awayTeamId) {
            throw new IllegalArgumentException("Home team, and away team are the same");
        }

        Team homeTeam = ApplicationRepository.getRepository().getTeamById(homeTeamId);
        Team awayTeam = ApplicationRepository.getRepository().getTeamById(awayTeamId);
        Stadium stadium = ApplicationRepository.getRepository().getStadiumByUUID(stadiumId);
        Referee referee = ApplicationRepository.getRepository().getRefereeByUUID(refereeId);

        if (!setHomeTeam(homeTeam) || !setAwayTeam(awayTeam)) {
            throw new IllegalArgumentException("Invalid home, or away team");
        }

        if ((stadium == null) || !stadium.checkStadiumAvailability(dateTime)) {
            throw new IllegalArgumentException("Invalid stadium");
        }

        if ((referee == null) || !referee.CheckRefereeAvailability(dateTime.toLocalDate())) {
            throw new IllegalArgumentException("Invalid referee");
        }

        setReferee(referee);
        setStadium(stadium);
    }

    public void delete() {
        Stadium stadium = getStadium();
        Referee referee = getReferee();

        if (stadium != null) {
            stadium.removeDateTimeFromSchedule(dateTime);
        }

        if (referee != null) {
            referee.removeDateFromSchedule(dateTime.toLocalDate());
        }
    }

    public boolean containsTeam(Team team) {
        return homeTeamId.equals(team.Id) || awayTeamId.equals(team.Id);
    }

    public Team getHomeTeam() {
        return ApplicationRepository.getRepository().getTeamById(homeTeamId);
    }

    public boolean setHomeTeam(Team team) {
        if (team == null) {
            this.homeTeamId = null;
            return true;
        }

        if (!ApplicationRepository.getRepository().containsTeam(team)) {
            return false;
        }

        this.homeTeamId = team.Id;
        return true;
    }

    public Team getAwayTeam() {
        return ApplicationRepository.getRepository().getTeamById(awayTeamId);
    }

    public boolean setAwayTeam(Team team) {
        if (team == null) {
            this.homeTeamId = null;
            return true;
        }

        if (!ApplicationRepository.getRepository().containsTeam(team)) {
            return false;
        }

        this.awayTeamId = team.Id;
        return true;
    }

    public Stadium getStadium() {
        return ApplicationRepository.getRepository().getStadiumByUUID(stadiumId);
    }

    public boolean setStadium(Stadium stadium) {
        if (stadium == null) {
            this.stadiumId = null;
            return true;
        }

        if (!stadium.checkStadiumAvailability(dateTime)) {
            return false;
        }

        if (this.stadiumId != null) {
            Stadium olStadium = getStadium();
            if (olStadium != null) {
                getStadium().removeDateTimeFromSchedule(dateTime);
            }
        }

        this.stadiumId = stadium.Id;
        stadium.addDateTimeToSchedule(dateTime);
        return true;
    }

    public Referee getReferee() {
        return ApplicationRepository.getRepository().getRefereeByUUID(refereeId);
    }

    public boolean setReferee(Referee referee) {
        if (referee == null) {
            this.refereeId = null;
            return true;
        }

        if (!referee.CheckRefereeAvailability(dateTime.toLocalDate())) {
            return false;
        }

        if (this.refereeId != null) {
            Referee oldReferee = getReferee();
            if (oldReferee != null) {
                oldReferee.removeDateFromSchedule(dateTime.toLocalDate());
            }
        }

        this.refereeId = referee.Id;
        referee.addDateToSchedule(dateTime.toLocalDate());
        return true;
    }

    public boolean setDate(LocalDateTime newDateTime) {
        Stadium stadium = getStadium();
        if ((stadium == null) || !stadium.checkStadiumAvailability(newDateTime)) {
            return false;
        }

        Referee referee = getReferee();
        if ((referee != null) && referee.CheckRefereeAvailability(dateTime.toLocalDate())) {
            referee.removeDateFromSchedule(dateTime.toLocalDate());
            referee.addDateToSchedule(newDateTime.toLocalDate());
        }

        stadium.removeDateTimeFromSchedule(dateTime);
        this.dateTime = newDateTime;
        stadium.addDateTimeToSchedule(newDateTime);
        return true;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HashMap<UUID, Integer> getScorers() {
        return scorers;
    }

    public boolean putGoals(UUID playerId, int numOfGoals) {
        if (numOfGoals <= 0) {
            return false;
        }

        scorers.put(playerId, numOfGoals);
        return true;
    }

    // public Referee setReferee(Referee referee) {
    // if (referee.CheckRefereeAvailability(this)) {
    // this.referee = referee;
    // return referee;
    // }

    // return null;
    // }

    public Team calcWinnerTeam() {
        int homeScore = 0;
        int awayScore = 0;

        Team homeTeam = getHomeTeam();
        Team awayTeam = getAwayTeam();

        if ((homeTeam == null) && (awayTeam == null)) {
            return null;
        }

        if (homeTeam == null) {
            return awayTeam;
        }

        if (awayTeam == null) {
            return homeTeam;
        }

        ArrayList<Player> homeTeamPlayers = homeTeam.getPlayers();
        ArrayList<Player> awayTeamPlayers = awayTeam.getPlayers();

        for (Player p : homeTeamPlayers) {
            if (scorers.containsKey(p.Id)) {
                homeScore += scorers.get(p.Id);
            }
        }

        for (Player p : awayTeamPlayers) {
            if (scorers.containsKey(p.Id)) {
                awayScore += scorers.get(p.Id);
            }
        }

        if (homeScore == awayScore) {
            return null;
        }

        return (homeScore > awayScore) ? homeTeam : awayTeam;
    }
}

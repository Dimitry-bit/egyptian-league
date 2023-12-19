package com.github.egyptian_league.DSA;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Match;
import com.github.egyptian_league.Models.Referee;
import com.github.egyptian_league.Models.Stadium;
import com.github.egyptian_league.Models.Team;

public class LeagueSchedulerTest {
    @Test
    public void generateMatchesTest() {
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(new Team("Team 1", null));
        teams.add(new Team("Team 2", null));
        teams.add(new Team("Team 3", null));
        teams.add(new Team("Team 4", null));

        ApplicationRepository.getRepository().putTeam(teams.get(0));
        ApplicationRepository.getRepository().putTeam(teams.get(1));
        ApplicationRepository.getRepository().putTeam(teams.get(2));
        ApplicationRepository.getRepository().putTeam(teams.get(3));

        ArrayList<LocalDateTime> timeSlots = new ArrayList<>();
        timeSlots.add(LocalDateTime.of(2023, 1, 1, 12, 0, 0));
        timeSlots.add(LocalDateTime.of(2023, 2, 2, 1, 0, 0));
        timeSlots.add(LocalDateTime.of(2023, 3, 3, 12, 0, 0));
        timeSlots.add(LocalDateTime.of(2023, 4, 4, 1, 0, 0));
        timeSlots.add(LocalDateTime.of(2023, 5, 5, 12, 0, 0));
        timeSlots.add(LocalDateTime.of(2023, 6, 6, 1, 0, 0));

        ArrayList<Stadium> stadiums = new ArrayList<>();
        stadiums.add(new Stadium("Stadium 1", "Stadium 1 Address"));
        stadiums.add(new Stadium("Stadium 2", "Stadium 2 Address"));

        ApplicationRepository.getRepository().putStadium(stadiums.get(0));
        ApplicationRepository.getRepository().putStadium(stadiums.get(1));

        ArrayList<Referee> referees = new ArrayList<>();
        referees.add(new Referee("Referee 1"));
        referees.add(new Referee("Referee 2"));

        ApplicationRepository.getRepository().putReferee(referees.get(0));
        ApplicationRepository.getRepository().putReferee(referees.get(1));

        LeagueScheduler ls = new LeagueScheduler(teams, stadiums, referees, timeSlots, new Random());
        List<Match> matches = ls.generateMatches();

        for (Match m : matches) {
            System.out.printf("'%s' vs. '%s', '%s', '%s', '%s'\n",
                    m.getHomeTeam().getName(),
                    m.getAwayTeam().getName(),
                    m.getStadium().getName(),
                    m.getReferee().getName(),
                    m.getDateTime().toString());
        }
    }
}

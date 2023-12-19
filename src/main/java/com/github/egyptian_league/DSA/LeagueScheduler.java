/*
 *
 * Copyright (c) 2023 Tony Medhat
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.egyptian_league.DSA;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.github.egyptian_league.Models.Match;
import com.github.egyptian_league.Models.Referee;
import com.github.egyptian_league.Models.Stadium;
import com.github.egyptian_league.Models.Team;
import com.github.egyptian_league.Utils.Pair;

public class LeagueScheduler {

    private final List<Team> teams;
    private final List<Stadium> stadiums;
    private final List<Referee> referees;
    private final List<LocalDateTime> timeSlots;

    public LeagueScheduler(
            ArrayList<Team> teams,
            ArrayList<Stadium> stadiums,
            ArrayList<Referee> referees,
            ArrayList<LocalDateTime> timeSlots,
            Random rnd) {

        if (teams.size() % 2 == 1) {
            throw new IllegalArgumentException("Team count is an odd number (%d)".formatted(teams.size()));
        }

        this.teams = teams;
        this.stadiums = stadiums;
        this.referees = referees;
        this.timeSlots = timeSlots;

        int timeSlotsCount = timeSlots.size();
        int mpr = RoundRobin.matchPerRound(teams.size());
        int round = RoundRobin.rounds(teams.size());

        if ((timeSlotsCount < (mpr * round))) {
            throw new IllegalArgumentException(
                    "Time slots count (%d) is less than matches count (%d)".formatted(timeSlotsCount, mpr * round));
        }

        // Collections.copy(this.teams, teams);
        Collections.shuffle(this.teams, rnd);
    }

    public List<Match> generateMatches() {
        List<ArrayList<Pair<Integer, Integer>>> rawMatches = RoundRobin.circle(teams.size());
        List<Match> populatedMatches = new ArrayList<>();

        for (int i = 0; i < rawMatches.size(); ++i) {
            for (int k = 0; k < rawMatches.get(i).size(); ++k) {
                Pair<Integer, Integer> p = rawMatches.get(i).get(k);

                Team homeTeam = teams.get(p.getKey() - 1);
                Team awayTeam = teams.get(p.getValue() - 1);
                Stadium stadium = stadiums.get(i % stadiums.size());
                Referee referee = referees.get(i % referees.size());

                Match match = new Match(homeTeam.Id, awayTeam.Id, stadium.id, referee.Id,
                        timeSlots.get((i * (rawMatches.size() - 1)) + k));

                populatedMatches.add(match);
            }
        }

        return populatedMatches;
    }
}

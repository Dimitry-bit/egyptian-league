package com.github.egyptian_league.Models;

import com.github.egyptian_league.ApplicationRepository;

import java.util.ArrayList;
import java.util.Iterator;

public class GameWeek {

    ArrayList<Match> matches = new ArrayList<>();
    ArrayList<Team> selectedTeams = new ArrayList<>();

    public void setMatches() {
        Iterator<Team> teamsIterator1 = ApplicationRepository.getRepository().getTeamsIterator();
        while (teamsIterator1.hasNext()) {
            Iterator<Team> teamIterator2 = ApplicationRepository.getRepository().getTeamsIterator();
            Team team1=teamsIterator1.next();
            while (teamIterator2.hasNext()) {
                Team team2=teamIterator2.next();
                if (!team1.equals(team2) && !choosenTeam(team1) && !choosenTeam(team2)){
                    Match match = new Match(team1.Id,team2.Id);
                    matches.add(match);
                    selectedTeams.add(team1);
                    selectedTeams.add(team2);
                    break;
                }
            }
        }
    }

    private boolean choosenTeam(Team team) {
        for (int i = 0; i < selectedTeams.size(); i++) {
            if (team.equals(selectedTeams.get(i))) {
                return true;
            }
        }
        return false;
    }
}

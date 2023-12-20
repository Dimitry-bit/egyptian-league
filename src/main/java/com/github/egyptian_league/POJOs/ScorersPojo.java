package com.github.egyptian_league.POJOs;

import com.github.egyptian_league.Models.Match;
import com.github.egyptian_league.Models.Player;
import com.github.egyptian_league.Models.Team;

public class ScorersPojo {
    private Player player;
    private Match match;

    public ScorersPojo(Player player, Match match) {
        this.player = player;
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public Match getMatch() {
        return match;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public String getTeamName() {
        Team team = player.getTeam();
        return (team != null) ? team.getName() : "";
    }

    public Integer getPlayerScore() {
        Integer goals = match.getScorers().get(player.Id);
        return (goals != null) ? goals : 0;
    }
}

package com.github.egyptian_league;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.UUID;

import com.github.egyptian_league.json.JsonSerializer;
import com.github.egyptian_league.json.JsonSerializerOptions;

public class ApplicationRepository {

    public static final ApplicationRepository defaultRepository = new ApplicationRepository();

    private Hashtable<UUID, Player> players;
    private Hashtable<UUID, Team> teams;
    private Hashtable<UUID, Match> matches;
    private Hashtable<UUID, Stadium> stadiums;
    private Hashtable<UUID, League> leagues;

    public ApplicationRepository() {
        players = new Hashtable<>();
        teams = new Hashtable<>();
        matches = new Hashtable<>();
        stadiums = new Hashtable<>();
        leagues = new Hashtable<>();
    }

    public void serialize(String baseDir, JsonSerializerOptions options) {
        // final String playersPath = baseDir + "players.json";
        // final String teamsPath = baseDir + "teams.json";
        // final String matchesPath = baseDir + "matches.json";
        // final String stadiumsPath = baseDir + "stadiums.json";
        // final String leaguesPath = baseDir + "leagues.json";

        // serialize(players, options, playersPath);
        // serialize(teams, options, teamsPath);
        // serialize(matches, options, matchesPath);
        // serialize(stadiums, options, stadiumsPath);
        // serialize(leagues, options, leaguesPath);
    }

    public void deserialize(String baseDir, JsonSerializerOptions options) {
        // final String playersPath = baseDir + "players.json";
        // final String teamsPath = baseDir + "teams.json";
        // final String matchesPath = baseDir + "matches.json";
        // final String stadiumsPath = baseDir + "stadiums.json";
        // final String leaguesPath = baseDir + "leagues.json";

        // try {
        //     // FIXME: This is a hack!

        //     Type t = ApplicationRepository.class.getDeclaredField("players").getGenericType();
        //     players = deserialize(t, options, playersPath);

        //     t = ApplicationRepository.class.getDeclaredField("teams").getGenericType();
        //     teams = deserialize(t, options, teamsPath);

        //     t = ApplicationRepository.class.getDeclaredField("matches").getGenericType();
        //     matches = deserialize(t, options, matchesPath);

        //     t = ApplicationRepository.class.getDeclaredField("stadiums").getGenericType();
        //     stadiums = deserialize(t, options, stadiumsPath);

        //     t = ApplicationRepository.class.getDeclaredField("leagues").getGenericType();
        //     leagues = deserialize(t, options, leaguesPath);
        // } catch (Exception e) {
        //     // Nothing
        // }
    }

    // #region Players

    public boolean containsPlayerUUID(UUID uuid) {
        return players.containsKey(uuid);
    }

    public boolean containsPlayer(Player player) {
        return players.containsValue(player);
    }

    public Player getPlayerByUUID(UUID uuid) {
        return players.get(uuid);
    }

    public Player[] getPlayersByName(String name) {
        String lName = name.toLowerCase();
        return (Player[]) players.values().stream()
                .filter(player -> lName.equals(player.getName().toLowerCase())).toArray();
    }

    public Player putPlayer(Player player) {
        return players.put(player.getId(), player);
    }

    public Iterator<Player> getPlayersIterator() {
        return players.values().iterator();
    }

    // #endregion

    // #region Teams

    public boolean containsTeamUUID(UUID uuid) {
        return teams.containsKey(uuid);
    }

    public boolean containsTeam(Team team) {
        return teams.containsValue(team);
    }

    public Team getTeamById(UUID uuid) {
        return teams.get(uuid);
    }

    public Team[] getTeamsByName(String name) {
        String lName = name.toLowerCase();
        return (Team[]) teams.values().stream()
                .filter(team -> lName.equals(team.getName().toLowerCase())).toArray();
    }

    public Iterator<Team> getTeamsIterator() {
        return teams.values().iterator();
    }
    public int getNumberOfTeams(){return teams.size();};

    public Team putTeam(Team team) {
        // return players.put(team.getId, team);
        throw new UnsupportedOperationException();
    }

    // #endregion

    // #region Matches

    public boolean containsMatchUUID(UUID uuid) {
        return matches.containsKey(uuid);
    }

    public boolean containsMatch(Match match) {
        return matches.containsValue(match);
    }

    public Match getMatchByUUID(UUID uuid) {
        return matches.get(uuid);
    }

    public Iterator<Match> getMatchesIterator() {
        return matches.values().iterator();
    }

    public Match putMatch(Match match) {
        return matches.put(match.matchId, match);
    }

    // #endregion

    // #region Stadiums

    public boolean containsStadiumUUID(UUID uuid) {
        return stadiums.containsKey(uuid);
    }

    public boolean containsStadium(Stadium stadium) {
        return stadiums.containsValue(stadium);
    }

    public Stadium getStadiumByUUID(UUID uuid) {
        return stadiums.get(uuid);
    }

    public Iterator<Stadium> getStadiumsIterator() {
        return stadiums.values().iterator();
    }

    public Stadium putStadium(Stadium stadium) {
        // return stadiums.put(, stadium);
        throw new UnsupportedOperationException();
    }

    // #endregion

    // #region Leagues

    public boolean containsLeagueUUID(UUID uuid) {
        return leagues.containsKey(uuid);
    }

    public boolean containsLeague(League league) {
        return leagues.containsValue(league);
    }

    public League getLeagueByUUID(UUID uuid) {
        return leagues.get(uuid);
    }

    public Iterator<League> getLeaguesIterator() {
        return leagues.values().iterator();
    }

    public League putLeague(League league) {
        // return stadiums.put(, stadium);
        throw new UnsupportedOperationException();
    }

    // #endregion

    private <T> void serialize(Hashtable<UUID, T> table, JsonSerializerOptions options, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            String json = JsonSerializer.serialize(table, options);
            if (!json.isEmpty() && !json.isBlank()) {
                writer.write(json);
            }
        } catch (IOException e) {
            System.out.printf("'%s': failed to write, %s\n", filePath, e.getMessage());
        }
    }

    private <T> Hashtable<UUID, T> deserialize(Type type, JsonSerializerOptions options, String filePath) {
        File fp = new File(filePath);
        if (!fp.exists()) {
            return new Hashtable<UUID, T>();
        }

        try {
            String JsonSource = String.join("\n", Files.readAllLines(Path.of(filePath)));
            return (Hashtable<UUID, T>) JsonSerializer.deserialize(JsonSource, type, options);
        } catch (IOException e) {
            System.out.printf("'%s': failed to read, %s\n", filePath, e.getMessage());
        }

        return null;
    }
}

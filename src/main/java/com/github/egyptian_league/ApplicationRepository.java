package com.github.egyptian_league;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.UUID;

import com.github.egyptian_league.Constants.ApplicationConstants;
import com.github.egyptian_league.json.JsonException;
import com.github.egyptian_league.json.JsonSerializer;
import com.github.egyptian_league.json.JsonSerializerOptions;
import com.github.egyptian_league.json.Annotations.JsonIgnore;

public class ApplicationRepository {

    @JsonIgnore
    private static ApplicationRepository instance;

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

    public static ApplicationRepository getRepository() {
        if (instance == null) {
            instance = new ApplicationRepository();
        }

        return instance;
    }

    public static void swapRepository(ApplicationRepository newRepository) {
        instance = newRepository;
    }

    public static void loadDb(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return;
            }

            if (f.isDirectory()) {
                System.err.printf("'%s': is a directory\n", f.getAbsolutePath());
                return;
            }

            if (!f.canRead()) {
                System.err.printf("'%s': failed to read\n", f.getAbsolutePath());
                return;
            }

            String jsonSource = String.join("", Files.readAllLines(f.toPath()));
            ApplicationRepository repo = JsonSerializer.deserialize(jsonSource, ApplicationRepository.class);

            swapRepository(repo);
        } catch (IOException e) {
            System.err.printf("'%s': failed to read\n\t%s", ApplicationConstants.dbFileName, e.getMessage());
        }
    }

    public static void saveDb(String path) {
        String json = null;

        try {
            JsonSerializerOptions options = new JsonSerializerOptions();
            options.WriteIndented = true;
            json = JsonSerializer.serialize(instance, options);
        } catch (JsonException e) {
            System.err.println("'%s': failed to serialize application repository, %s".formatted(path, e.getMessage()));
        }

        if (json == null) {
            return;
        }

        try (FileWriter fw = new FileWriter(path)) {
            fw.write(json);
        } catch (IOException e) {
            System.err.printf("'%s': failed to write\n\t%s", ApplicationConstants.dbFileName, e.getMessage());
        }
    }

    // #region Players

    public boolean containsPlayerUUID(UUID uuid) {
        return players.containsKey(uuid);
    }

    public boolean containsPlayer(Player player) {
        return players.containsValue(player);
    }

    public boolean containsPlayerName(String name) {
        String lName = name.toLowerCase();
        return players.values().stream().anyMatch(player -> lName.equals(player.getName().toLowerCase()));
    }

    public Player getPlayerByUUID(UUID uuid) {
        return players.get(uuid);
    }

    public Player[] getPlayersByName(String name) {
        String lName = name.toLowerCase();
        return players.values().stream()
                .filter(player -> lName.equals(player.getName().toLowerCase())).toArray(Player[]::new);
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

    public boolean containsTeamName(String name) {
        String lName = name.toLowerCase();
        return teams.values().stream().anyMatch(team -> lName.equals(team.getName().toLowerCase()));
    }

    public Team getTeamById(UUID uuid) {
        return teams.get(uuid);
    }

    public Team[] getTeamsByName(String name) {
        String lName = name.toLowerCase();
        return teams.values().stream()
                .filter(team -> lName.equals(team.getName().toLowerCase())).toArray(Team[]::new);
    }

    public Iterator<Team> getTeamsIterator() {
        return teams.values().iterator();
    }

    public int getNumberOfTeams() {
        return teams.size();
    };

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
}

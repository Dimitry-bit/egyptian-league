import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class Match {
    public final UUID matchId;
    private LocalDateTime dateTime;
    public final UUID HomeTeamId;
    public final UUID AwayTeamId;
    public final Referee Referee;
    private HashMap<UUID, Integer> goalScorers;

    public Match(UUID homeTeamId, UUID awayTeamId, Referee Referee) {
        matchId = UUID.randomUUID();
        this.HomeTeamId = homeTeamId;
        this.AwayTeamId = awayTeamId;
        this.Referee = Referee;
    }

    public HashMap<UUID, Integer> getGoalScorers() {
        return goalScorers;
    }

    public void AddGoal(UUID playerID, int numOfGoals) {
        goalScorers.put(playerID, numOfGoals);
    }

    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

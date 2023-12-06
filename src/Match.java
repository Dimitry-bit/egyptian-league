import java.util.Date;
import java.util.HashMap;

public class Match {
    private static int matchIdGenerator = 1;
    public final int matchID;
    public final Date Date;
    public final Team HomeTeam;
    public final Team AwayTeam;
    public final String StadiumName;
    public final Referee Referee;
    private HashMap<Integer, Integer> goalScorers;

    public Match(Date date, Team homeTeam, Team awayTeam, String stadiumName, Referee referee) {
        matchID = matchIdGenerator;
        this.Date = date;
        this.HomeTeam = homeTeam;
        this.AwayTeam = awayTeam;
        this.StadiumName = stadiumName;
        this.Referee = referee;
        matchIdGenerator++;
    }

    public HashMap<Integer, Integer> getGoalScorers() {
        return goalScorers;
    }

    public void AddGoal(int playerID, int numOfGoals) {
        goalScorers.put(playerID, numOfGoals);
    }

}

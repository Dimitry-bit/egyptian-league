import java.sql.Time;
import java.util.Date;
import java.util.HashMap;

public class Match {
    private static int matchIdGenerator = 1;
    public final int matchID;
    private Date date;
    private Time time;
    public final Team HomeTeam;
    public final Team AwayTeam;
    public final Referee Referee;
    private HashMap<Integer, Integer> goalScorers;

    public Match(Team homeTeam, Team awayTeam ,Referee Referee) {
        matchID = matchIdGenerator;
        this.HomeTeam = homeTeam;
        this.AwayTeam = awayTeam;
        this.Referee = Referee;
        matchIdGenerator++;
    }

    public HashMap<Integer, Integer> getGoalScorers() {
        return goalScorers;
    }

    public void AddGoal(int playerID, int numOfGoals) {
        goalScorers.put(playerID, numOfGoals);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

}

import java.util.Date;

public class Match {
    private static int matchIdGenerator = 1;
    public final int matchID;
    public final Date date;
    public final Team homeTeam;
    public final Team awayTeam;
    public final String stadiumName;
    public final int homeScore;
    public final int awayScore;
    public final Referee referee;

    public Match(Date date, Team homeTeam, Team awayTeam, String stadiumName, Referee referee, int homeScore, int awayScore) {
        matchID = matchIdGenerator;
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.stadiumName = stadiumName;
        this.referee = referee;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        matchIdGenerator++;
    }

    public Match() {
        matchID=matchIdGenerator;
        matchIdGenerator++;
    }

    public Match(Team homeTeam, Team awayTeam, int homeScore, int awayScore) {
        matchID=matchIdGenerator;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        matchIdGenerator++;
    }

    public Date getDate() {
        return date;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public Referee getReferee() {
        return referee;
    }
    
}

public class Score {
  private int homeScore;
  private int awayScore;
    public Score(int homeScore,int awayScore){
        this.awayScore=awayScore;
        this.homeScore=homeScore;
    }
    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }
}

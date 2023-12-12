import java.util.ArrayList;
import java.util.Scanner;

public class player {
    private String name;
    private String team;
    private String position;
    private int id;
    private int number;
    private int age;
    private int GoalScored;
    private int Rank;

    public player(String name, String team, String position, int id, int number, int age, int GoalScored, int Rank) {

        this.name = name;
        this.Rank = Rank;
        this.GoalScored = GoalScored;
        this.team = team;
        this.age = age;
        this.position = position;
        this.number = number;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    public void setGoalScored(int goalScored) {
        GoalScored = goalScored;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getAge() {
        return age;
    }

    public int getRank() {
        return Rank;
    }

    public int getGoalScored() {
        return GoalScored;
    }

    public String getTeam() {
        return team;
    }


   public void


          setPlayers (ArrayList<player> players) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the player name");
        String name = scanner.nextLine();
        for (int i = 0; i < players.size(); i++) {
            if (name.equals(players.get(i).getName())) {
                System.out.println(players.get(i).getName() + "  " + players.get(i).getTeam() + "  " + players.get(i).getAge() + "  " + players.get(i).getGoalScored() + "  " + players.get(i).getNumber() + "  " + players.get(i).getId() + "  " + players.get(i).getRank() + "  " + players.get(i).getPosition());


            }
        }
    }
}

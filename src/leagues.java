import java.util.ArrayList;

import java.util.Calendar;

public class leagues {
    public final String league_Name;
private String name;
    private int  year;
    private  ArrayList<Team> teams=new ArrayList<>();
private ArrayList<player> Players=new ArrayList<>();

    public leagues(String name, int year)throws sye {
        this.name = name;
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        if(year<currentYear){

            try {
                throw new sye(year,currentYear);
            } catch (sye e) {
                throw new RuntimeException(e);
            }
        }
       else {
            this.year = year;
        }
        league_Name =name+" "+year;
    }






    public void getplayers() {
        for(player x:Players){
            int i=0;
            System.out.println(Players.get(0).getId());
            System.out.println(Players.get(0).getAge());
            System.out.println(Players.get(0).getGoalScored());
            System.out.println(Players.get(0).getName());
            System.out.println(Players.get(0).getPosition());
            System.out.println(Players.get(0).getTeam());
            System.out.println(Players.get(0).getNumber());
            System.out.println(Players.get(0).getRank());
            i++;
        }
    }

    public void setPlayers(String name, String team, String position, int id, int number, int age, int GoalScored, int Rank) {
        Players.add(new player(name,team,position,id,number,age,GoalScored,Rank));
    }
    public void getteams() {
        for(Team x:teams){
            int i=0;
            System.out.println(teams.get(0).);
            System.out.println(teams.get(0).);
            System.out.println(teams.get(0).);
            System.out.println(teams.get(0).);

            i++;
        }
    }

    public void setTeams(String Name, int Team_ID, int Total_Score, player Captain) {
        Players.add(new player(( Name,  Team_ID,  Total_Score,  Captain));
    }

}





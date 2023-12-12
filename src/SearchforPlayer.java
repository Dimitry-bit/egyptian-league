import java.util.ArrayList;
import java.util.Scanner;

public class SearchforPlayer {
    public player Searchforplayer     (ArrayList<player> players) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the player name");
        String name = scanner.nextLine();
        for (player value : players) {

            player player = null;
            if (name.equals(value.getName()))

                return player;
        }


  }}


package com.github.egyptian_league.CLI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Match;
import com.github.egyptian_league.Models.Referee;
import com.github.egyptian_league.Models.Stadium;
import com.github.egyptian_league.Models.Team;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static com.github.egyptian_league.CLI.CLI_Input.clearCli;

public class AddMatch extends MenuItem{


    public AddMatch(String name, MenuItem Back) {
        super(name, Back);
    }

    Scanner in = new Scanner(System.in);
    @Override
    public boolean update() {

        clearCli();


        Iterator<Team> TeamsIterator = ApplicationRepository.getRepository().getTeamsIterator();
        ArrayList<Team> Teams = new ArrayList<>();

        Iterator<Stadium> StadiumsIterator = ApplicationRepository.getRepository().getStadiumsIterator();
        ArrayList<Stadium> Stadiums = new ArrayList<>();

        Iterator<Referee> RefereesIterator = ApplicationRepository.getRepository().getRefereesIterator();
        ArrayList<Referee> Referees = new ArrayList<>();


        while (TeamsIterator.hasNext()) {
            Team team = TeamsIterator.next();
            Teams.add(team);
        }

        while (StadiumsIterator.hasNext()) {
            Stadium stadium = StadiumsIterator.next();
            Stadiums.add(stadium);
        }

        while (RefereesIterator.hasNext()) {
            Referee referee = RefereesIterator.next();
            Referees.add(referee);
        }

        int TotalTeams = Teams.size();
        int TotalStadiums = Stadiums.size();
        int TotalReferees = Referees.size();

        if (TotalTeams < 2 || TotalStadiums == 0 || TotalReferees == 0)
        {
            System.out.println("Error: At least 2 teams, 1 stadium and 1 referee is required to add a match");
            System.out.println("Press Enter key to continue...");

            try
            {
                System.in.read();
                in.nextLine();
            }
            catch(Exception e)
            {}

            back();

            return true;
        }



        boolean isValid = true;
        int HomeChoice;
        do {
            clearCli();

            if (!isValid) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + TotalTeams);
            }
            System.out.println("Enter 'x' at any point to exit adding team. HOWEVER exiting prematurely will DISCARD all your entered data");
            System.out.println("Choose home team:");


            for (int i = 0; i < TotalTeams; i++) {
                System.out.printf("%d] %s\n", i + 1, Teams.get(i).getName());
            }

            System.out.println("Enter your choice: ");

            String input = in.nextLine();

            try {
                HomeChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                if (input.length() == 1 && input.charAt(0) == 'x'){
                    back();
                    return true;
                }
                else{
                    HomeChoice = 0;
                }
            }

            if (( HomeChoice < 1 ||  HomeChoice > TotalTeams)) {
                isValid = false;
            }
            else{
                isValid = true;
            }
        }while(!isValid);



        isValid = true;
        int AwayChoice;
        do {
            clearCli();

            if (!isValid) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + TotalTeams);
            }
            System.out.println("Choose away team:");


            for (int i = 0; i < TotalTeams; i++) {
                System.out.printf("%d] %s\n", i + 1, Teams.get(i).getName());
            }

            System.out.println("Enter your choice: ");

            String input = in.nextLine();

            try {
                AwayChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                if (input.length() == 1 && input.charAt(0) == 'x')
                {
                    back();
                    return true;
                }
                else {
                    AwayChoice = 0;
                }
            }

            if ((AwayChoice < 1 || AwayChoice > TotalTeams)) {
                isValid = false;
            }
            else{
                isValid = true;
            }
        }while(!isValid);



        isValid = true;
        int StadiumChoice;
        do {
            clearCli();

            if (!isValid) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + TotalStadiums);
            }
            System.out.println("Choose stadium:");


            for (int i = 0; i < TotalStadiums; i++) {
                System.out.printf("%d] %s loc: %s\n", i + 1, Stadiums.get(i).getName(), Stadiums.get(i).getAddress());
            }

            System.out.println("Enter your choice: ");

            String input = in.nextLine();

            try {
                StadiumChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                if (input.length() == 1 && input.charAt(0) == 'x')
                {
                    back();
                    return true;
                }
                else{
                    StadiumChoice = 0;
                }
            }

            if ((StadiumChoice < 1 ||StadiumChoice > TotalTeams) && StadiumChoice != 'x') {
                isValid = false;
            }
            else{
                isValid = true;
            }
        }while(!isValid);


        isValid = true;
        int RefereeChoice;
        do {
            clearCli();

            if (!isValid) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and " + TotalReferees);
            }
            System.out.println("Choose referee:");


            for (int i = 0; i < TotalReferees; i++) {
                System.out.printf("%d] %s\n", i + 1, Referees.get(i).getName());
            }

            System.out.println("Enter your choice: ");

            String input = in.nextLine();

            try {
                RefereeChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                if (input.length() == 1 && input.charAt(0) == 'x'){
                    back();
                    return true;
                }
                else {
                    RefereeChoice = 0;
                }

            }

            if ((RefereeChoice < 1 ||RefereeChoice > TotalTeams)) {
                isValid = false;
            }
            else{
                isValid = true;
            }
        }while(!isValid);


        isValid = true;
        LocalDateTime parsedDate = null;
        do {
            if (!isValid) {
                System.out.println("Invalid Date and/or Time format");
                isValid = true;
            }
            System.out.println("Enter the date of the match in YYYY-MM-DD format");
            String Date = in.nextLine();

            if (Date.length() == 1 && Date.charAt(0) == 'x')
            {
                back();
                return true;
            }

            System.out.println("Enter the time of the match in HH:MM format");
            String Time = in.nextLine();

            if (Time.length() == 1 && Time.charAt(0) == 'x')
            {
                back();
                return true;
            }

            String ToBeParsed = Date + "T" + Time + ":00";

            try {
                parsedDate = LocalDateTime.parse(ToBeParsed);
            } catch (DateTimeParseException e) {
                isValid = false;
            }

        }while (!isValid);


        System.out.printf("Home team: %s\n Away team: %s\nStadium: %s\nReferee: %s\nDate: %s\n",
                Teams.get(HomeChoice-1).getName(), Teams.get(AwayChoice-1).getName(),
                Stadiums.get(StadiumChoice-1).getName(), Referees.get(RefereeChoice-1).getName(),
                parsedDate);

        char c;
        do {
            System.out.println("Save this Match? [y to save, n to DISCARD!! all entered data]: ");
            c = in.nextLine().charAt(0);
        }while(c != 'y' && c != 'Y' && c != 'n' && c != 'N');

        if (c == 'y' || c == 'Y')
        {
            try {
                ApplicationRepository.getRepository().putMatch(new Match(
                        Teams.get(HomeChoice-1).Id, Teams.get(AwayChoice-1).Id,
                        Stadiums.get(StadiumChoice-1).Id, Referees.get(RefereeChoice-1).Id,parsedDate));

            }
            catch (IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
                System.out.println("Press Enter key to input a valid match...");
                try
                {
                    System.in.read();
                    in.nextLine();
                }
                catch(Exception ee)
                {}
                return true;
            }

            back();
        }
        else {
            back();
        }

        return true;
    }

}

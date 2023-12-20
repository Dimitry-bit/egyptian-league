package com.github.egyptian_league.Seeds;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SeedDb {

    public static void seed() {
        ArrayList<Team> teams = new ArrayList<>();
        Player[] players = new Player[11];

        players[0] = new Player("Zizo", null, LocalDate.of(1996, 1, 10), Position.FORWARD, 25);
        players[1] = new Player("Jaziri", null, LocalDate.of(1993, 2, 12), Position.FORWARD, 30);
        players[2] = new Player("Obama", null, LocalDate.of(1995, 5, 26), Position.MIDFIELD, 14);
        players[3] = new Player("Donga", null, LocalDate.of(1996, 4, 6), Position.MIDFIELD, 8);
        players[4] = new Player("Roka", null, LocalDate.of(1993, 7, 10), Position.MIDFIELD, 12);
        players[5] = new Player("Shifo", null, LocalDate.of(1985, 7, 1), Position.DEFENDER, 29);
        players[6] = new Player("Wensh", null, LocalDate.of(1995, 6, 1), Position.DEFENDER, 28);
        players[7] = new Player("Mathlouthi", null, LocalDate.of(1992, 7, 25), Position.DEFENDER, 24);
        players[8] = new Player("Fatouh", null, LocalDate.of(1998, 3, 21), Position.DEFENDER, 13);
        players[9] = new Player("Awad", null, LocalDate.of(1991, 9, 30), Position.GOALKEEPER, 1);
        players[10] = new Player("Shikabala", null, LocalDate.of(1986, 3, 5), Position.FORWARD, 10);

        teams.add(insertTeam("Zamalek", players, 10));

        players[0] = new Player("Maaloul", null, LocalDate.of(1990, 1, 1), Position.DEFENDER, 21);
        players[1] = new Player("Tawfik", null, LocalDate.of(1997, 11, 8), Position.DEFENDER, 8);
        players[2] = new Player("Hany", null, LocalDate.of(1996, 1, 25), Position.DEFENDER, 30);
        players[3] = new Player("Yasser", null, LocalDate.of(1993, 2, 10), Position.DEFENDER, 6);
        players[4] = new Player("Ashour", null, LocalDate.of(1998, 2, 20), Position.MIDFIELD, 22);
        players[5] = new Player("Afsha", null, LocalDate.of(1996, 3, 6), Position.MIDFIELD, 19);
        players[6] = new Player("Dieng", null, LocalDate.of(1997, 10, 16), Position.MIDFIELD, 23);
        players[7] = new Player("Tau", null, LocalDate.of(1994, 5, 13), Position.FORWARD, 10);
        players[8] = new Player("Shahat", null, LocalDate.of(1992, 6, 21), Position.FORWARD, 14);
        players[9] = new Player("Kahraba", null, LocalDate.of(1994, 4, 13), Position.FORWARD, 7);
        players[10] = new Player("Shenawy", null, LocalDate.of(1988, 12, 18), Position.GOALKEEPER, 1);

        teams.add(insertTeam("Ahly", players, 10));

        players[0] = new Player("Ekramy", null, LocalDate.of(1983, 7, 10), Position.GOALKEEPER, 22);
        players[1] = new Player("Samy", null, LocalDate.of(1992, 5, 31), Position.DEFENDER, 4);
        players[2] = new Player("Gabr", null, LocalDate.of(1989, 1, 9), Position.DEFENDER, 5);
        players[3] = new Player("Chibi", null, LocalDate.of(1993, 1, 21), Position.DEFENDER, 15);
        players[4] = new Player("Hamdy", null, LocalDate.of(1995, 3, 15), Position.DEFENDER, 21);
        players[5] = new Player("Toure", null, LocalDate.of(1994, 8, 4), Position.MIDFIELD, 7);
        players[6] = new Player("Karti", null, LocalDate.of(1997, 7, 23), Position.MIDFIELD, 18);
        players[7] = new Player("Abdullah", null, LocalDate.of(1985, 7, 13), Position.MIDFIELD, 19);
        players[8] = new Player("Ramadan", null, LocalDate.of(1997, 1, 23), Position.FORWARD, 10);
        players[9] = new Player("Adel", null, LocalDate.of(2001, 5, 1), Position.FORWARD, 30);
        players[10] = new Player("Mayele", null, LocalDate.of(1994, 6, 24), Position.FORWARD, 9);

        teams.add(insertTeam("Pyramids", players, 10));

        players[0] = new Player("fawzi", null, LocalDate.of(1993, 7, 10), Position.GOALKEEPER, 1);
        players[1] = new Player("Nagguez", null, LocalDate.of(1992, 10, 28), Position.DEFENDER, 21);
        players[2] = new Player("Arafat", null, LocalDate.of(1993, 5, 26), Position.DEFENDER, 24);
        players[3] = new Player("Mohammed", null, LocalDate.of(2002, 9, 1), Position.DEFENDER, 26);
        players[4] = new Player("Desoky", null, LocalDate.of(1997, 12, 18), Position.DEFENDER, 3);
        players[5] = new Player("Gomaa", null, LocalDate.of(1993, 8, 1), Position.MIDFIELD, 14);
        players[6] = new Player("Bayoumi", null, LocalDate.of(2000, 4, 7), Position.MIDFIELD, 8);
        players[7] = new Player("Makhlof", null, LocalDate.of(1998, 1, 1), Position.MIDFIELD, 6);
        players[8] = new Player("Shabrawy", null, LocalDate.of(1994, 12, 15), Position.FORWARD, 7);
        players[9] = new Player("Morsi", null, LocalDate.of(1992, 1, 1), Position.FORWARD, 17);
        players[10] = new Player("Wagdy", null, LocalDate.of(2002, 3, 4), Position.FORWARD, 23);

        teams.add(insertTeam("Ismaily", players, 10));

        players[0] = new Player("Genish", null, LocalDate.of(1987, 7, 9), Position.GOALKEEPER, 16);
        players[1] = new Player("Basem", null, LocalDate.of(1988, 10, 27), Position.DEFENDER, 2);
        players[2] = new Player("Rizk", null, LocalDate.of(1990, 1, 1), Position.DEFENDER, 4);
        players[3] = new Player("Fil", null, LocalDate.of(1992, 12, 13), Position.DEFENDER, 6);
        players[4] = new Player("Taha", null, LocalDate.of(1991, 9, 4), Position.DEFENDER, 11);
        players[5] = new Player("Nasser", null, LocalDate.of(1997, 2, 8), Position.MIDFIELD, 10);
        players[6] = new Player("Ghanam", null, LocalDate.of(1997, 3, 12), Position.MIDFIELD, 27);
        players[7] = new Player("Zaazaa", null, LocalDate.of(2001, 6, 23), Position.MIDFIELD, 37);
        players[8] = new Player("barry", null, LocalDate.of(1991, 9, 27), Position.FORWARD, 3);
        players[9] = new Player("Atef", null, LocalDate.of(1998, 3, 21), Position.FORWARD, 30);
        players[10] = new Player("Balaha", null, LocalDate.of(1996, 6, 21), Position.FORWARD, 8);

        teams.add(insertTeam("Future", players, 10));

        players[0] = new Player("Gad", null, LocalDate.of(1997, 5, 22), Position.GOALKEEPER, 27);
        players[1] = new Player("Eraki", null, LocalDate.of(1996, 10, 9), Position.DEFENDER, 7);
        players[2] = new Player("Dabash", null, LocalDate.of(1990, 11, 25), Position.DEFENDER, 4);
        players[3] = new Player("Moussa", null, LocalDate.of(1988, 4, 11), Position.DEFENDER, 8);
        players[4] = new Player("Othman", null, LocalDate.of(2004, 1, 4), Position.DEFENDER, 46);
        players[5] = new Player("Jelassi", null, LocalDate.of(1994, 2, 7), Position.MIDFIELD, 14);
        players[6] = new Player("Attia", null, LocalDate.of(1998, 12, 3), Position.MIDFIELD, 15);
        players[7] = new Player("Deghmowm", null, LocalDate.of(1998, 12, 2), Position.MIDFIELD, 18);
        players[8] = new Player("Etouga", null, LocalDate.of(2001, 9, 18), Position.FORWARD, 37);
        players[9] = new Player("Greisha", null, LocalDate.of(1997, 1, 1), Position.FORWARD, 26);
        players[10] = new Player("Guenaoui", null, LocalDate.of(1998, 8, 2), Position.FORWARD, 23);

        teams.add(insertTeam("Masry", players, 10));

        players[0] = new Player("Elsayed", null, LocalDate.of(1992, 3, 1), Position.GOALKEEPER, 29);
        players[1] = new Player("Sabeha", null, LocalDate.of(1999, 9, 9), Position.DEFENDER, 13);
        players[2] = new Player("Kalosha", null, LocalDate.of(1998, 12, 7), Position.DEFENDER, 24);
        players[3] = new Player("Dowidar", null, LocalDate.of(1999, 2, 7), Position.DEFENDER, 30);
        players[4] = new Player("Khashab", null, LocalDate.of(1998, 7, 26), Position.DEFENDER, 26);
        players[5] = new Player("Shakshak", null, LocalDate.of(2000, 4, 6), Position.MIDFIELD, 14);
        players[6] = new Player("Ngom", null, LocalDate.of(2003, 12, 4), Position.MIDFIELD, 31);
        players[7] = new Player("Elagouz", null, LocalDate.of(1993, 5, 4), Position.MIDFIELD, 22);
        players[8] = new Player("rafik", null, LocalDate.of(1992, 10, 28), Position.FORWARD, 10);
        players[9] = new Player("Nahas", null, LocalDate.of(2002, 2, 21), Position.FORWARD, 42);
        players[10] = new Player("Maleg", null, LocalDate.of(1994, 10, 1), Position.FORWARD, 15);

        teams.add(insertTeam("Enppi", players, 10));

        players[0] = new Player("Soliman", null, LocalDate.of(1984, 7, 8), Position.GOALKEEPER, 1);
        players[1] = new Player("Wahid", null, LocalDate.of(1994, 6, 19), Position.DEFENDER, 33);
        players[2] = new Player("Haggag", null, LocalDate.of(1997, 10, 1), Position.DEFENDER, 4);
        players[3] = new Player("Mijahed", null, LocalDate.of(2001, 8, 20), Position.DEFENDER, 31);
        players[4] = new Player("Mido", null, LocalDate.of(1993, 11, 30), Position.DEFENDER, 34);
        players[5] = new Player("Gaber", null, LocalDate.of(1996, 1, 5), Position.MIDFIELD, 37);
        players[6] = new Player("AbdelHalim", null, LocalDate.of(1995, 1, 1), Position.MIDFIELD, 17);
        players[7] = new Player("Faisal", null, LocalDate.of(1999, 3, 4), Position.MIDFIELD, 32);
        players[8] = new Player("Ajayi", null, LocalDate.of(1996, 1, 25), Position.FORWARD, 10);
        players[9] = new Player("Makrona", null, LocalDate.of(2002, 9, 6), Position.FORWARD, 38);
        players[10] = new Player("Lhezeuo", null, LocalDate.of(2001, 11, 28), Position.FORWARD, 42);

        teams.add(insertTeam("Smouha", players, 10));

        Stadium cairo = new Stadium("Cairo Stadium", "Nasr City");
        Stadium borgArab = new Stadium("Borg El Arab Stadium", "Borg El Arab");
        Stadium petrosport = new Stadium("Petrosport Stadium", "New Cairo");
        Stadium elSalam = new Stadium("El Salam Stadium", "El Salam City");

        ApplicationRepository.getRepository().putStadium(cairo);
        ApplicationRepository.getRepository().putStadium(borgArab);
        ApplicationRepository.getRepository().putStadium(petrosport);
        ApplicationRepository.getRepository().putStadium(elSalam);

        Referee ibrahim = new Referee("Ibrahim");
        Referee ghandour = new Referee("Ghandour");
        Referee elbana = new Referee("El Bana");
        Referee nagy = new Referee("nagy");
        Referee maarouf = new Referee("Maarouf");

        Match []matches=new Match[14*4];
        matches[0]=new Match(teams.get(0).Id,teams.get(2).Id,cairo.id,nagy.Id, LocalDateTime.of(2023,12,21,20,0));
        matches[1]=new Match(teams.get(1).Id,teams.get(4).Id,borgArab.id,ghandour.Id, LocalDateTime.of(2023,12,22,18,0));
        matches[2]=new Match(teams.get(5).Id,teams.get(3).Id,petrosport.id,maarouf.Id, LocalDateTime.of(2023,12,23,14,0));
        matches[3]=new Match(teams.get(6).Id,teams.get(7).Id,elSalam.id,elbana.Id, LocalDateTime.of(2023,12,24,21,0));
        matches[4]=new Match(teams.get(0).Id,teams.get(1).Id,cairo.id,nagy.Id, LocalDateTime.of(2023,12,31,20,0));
        matches[5]=new Match(teams.get(2).Id,teams.get(4).Id,petrosport.id,ibrahim.Id, LocalDateTime.of(2023,1,1,20,0));
        matches[6]=new Match(teams.get(5).Id,teams.get(7).Id,elSalam.id,elbana.Id, LocalDateTime.of(2023,1,2,19,0));
        matches[7]=new Match(teams.get(3).Id,teams.get(6).Id,borgArab.id,maarouf.Id, LocalDateTime.of(2023,1,3,20,0));
        matches[8]=new Match(teams.get(0).Id,teams.get(4).Id,petrosport.id,maarouf.Id, LocalDateTime.of(2023,1,10,20,0));
        matches[9]=new Match(teams.get(1).Id,teams.get(2).Id,borgArab.id,ibrahim.Id, LocalDateTime.of(2023,1,11,21,0));
        matches[10]=new Match(teams.get(5).Id,teams.get(6).Id,elSalam.id,elbana.Id, LocalDateTime.of(2023,1,11,20,0));
        matches[11]=new Match(teams.get(3).Id,teams.get(7).Id,cairo.id,nagy.Id, LocalDateTime.of(2023,1,12,20,0));
        matches[12]=new Match(teams.get(0).Id,teams.get(6).Id,elSalam.id,elbana.Id, LocalDateTime.of(2023,1,18,20,0));
        matches[13]=new Match(teams.get(1).Id,teams.get(3).Id,petrosport.id,ibrahim.Id, LocalDateTime.of(2023,1,20,21,0));
        matches[14]=new Match(teams.get(2).Id,teams.get(5).Id,elSalam.id,maarouf.Id, LocalDateTime.of(2023,1,20,20,0));
        matches[15]=new Match(teams.get(0).Id,teams.get(7).Id,cairo.id,nagy.Id, LocalDateTime.of(2023,1,27,20,0));
        matches[16]=new Match(teams.get(1).Id,teams.get(6).Id,borgArab.id,maarouf.Id, LocalDateTime.of(2023,1,28,20,0));
        matches[17]=new Match(teams.get(2).Id,teams.get(3).Id,cairo.id,ibrahim.Id, LocalDateTime.of(2023,1,28,20,0));
        matches[18]=new Match(teams.get(4).Id,teams.get(5).Id,elSalam.id,elbana.Id, LocalDateTime.of(2023,1,29,20,0));
        matches[19]=new Match(teams.get(0).Id,teams.get(3).Id,petrosport.id,maarouf.Id, LocalDateTime.of(2023,1,30,20,0));
        matches[20]=new Match(teams.get(0).Id,teams.get(5).Id,borgArab.id,nagy.Id, LocalDateTime.of(2023,2,4,20,0));
        matches[21]=new Match(teams.get(1).Id,teams.get(5).Id,cairo.id,ibrahim.Id, LocalDateTime.of(2023,2,5,20,0));
        matches[22]=new Match(teams.get(1).Id,teams.get(7).Id,borgArab.id,maarouf.Id, LocalDateTime.of(2023,2,14,20,0));
        matches[23]=new Match(teams.get(2).Id,teams.get(6).Id,elSalam.id,nagy.Id, LocalDateTime.of(2023,2,3,20,0));
        matches[24]=new Match(teams.get(2).Id,teams.get(7).Id,petrosport.id,elbana.Id, LocalDateTime.of(2023,2,10,18,0));
        matches[25]=new Match(teams.get(3).Id,teams.get(4).Id,cairo.id,ibrahim.Id, LocalDateTime.of(2023,2,5,20,0));
        matches[26]=new Match(teams.get(4).Id,teams.get(6).Id,borgArab.id,nagy.Id, LocalDateTime.of(2023,2,14,20,0));
        matches[27]=new Match(teams.get(4).Id,teams.get(7).Id,elSalam.id,maarouf.Id, LocalDateTime.of(2023,2,22,20,0));
        matches[28]=new Match(teams.get(2).Id,teams.get(0).Id,cairo.id,nagy.Id, LocalDateTime.of(2023,3,1,20,0));
        matches[29]=new Match(teams.get(4).Id,teams.get(1).Id,borgArab.id,ghandour.Id, LocalDateTime.of(2023,3,2,18,0));
        matches[30]=new Match(teams.get(3).Id,teams.get(5).Id,petrosport.id,maarouf.Id, LocalDateTime.of(2023,3,3,14,0));
        matches[31]=new Match(teams.get(7).Id,teams.get(6).Id,elSalam.id,elbana.Id, LocalDateTime.of(2023,3,4,21,0));
        matches[32]=new Match(teams.get(1).Id,teams.get(0).Id,cairo.id,nagy.Id, LocalDateTime.of(2023,3,5,20,0));
        matches[33]=new Match(teams.get(4).Id,teams.get(2).Id,petrosport.id,ibrahim.Id, LocalDateTime.of(2023,3,11,20,0));
        matches[34]=new Match(teams.get(7).Id,teams.get(5).Id,elSalam.id,elbana.Id, LocalDateTime.of(2023,3,12,19,0));
        matches[35]=new Match(teams.get(6).Id,teams.get(3).Id,borgArab.id,maarouf.Id, LocalDateTime.of(2023,3,13,20,0));
        matches[36]=new Match(teams.get(4).Id,teams.get(0).Id,petrosport.id,maarouf.Id, LocalDateTime.of(2023,3,14,20,0));
        matches[37]=new Match(teams.get(2).Id,teams.get(1).Id,borgArab.id,ibrahim.Id, LocalDateTime.of(2023,3,21,21,0));
        matches[38]=new Match(teams.get(6).Id,teams.get(5).Id,elSalam.id,elbana.Id, LocalDateTime.of(2023,3,21,20,0));
        matches[39]=new Match(teams.get(7).Id,teams.get(3).Id,cairo.id,nagy.Id, LocalDateTime.of(2023,3,22,22,0));
        matches[40]=new Match(teams.get(6).Id,teams.get(0).Id,elSalam.id,elbana.Id, LocalDateTime.of(2023,3,23,20,0));
        matches[41]=new Match(teams.get(3).Id,teams.get(1).Id,petrosport.id,ibrahim.Id, LocalDateTime.of(2023,3,31,21,0));
        matches[42]=new Match(teams.get(5).Id,teams.get(2).Id,elSalam.id,maarouf.Id, LocalDateTime.of(2023,4,2,20,0));
        matches[43]=new Match(teams.get(7).Id,teams.get(0).Id,cairo.id,nagy.Id, LocalDateTime.of(2023,4,3,20,0));
        matches[44]=new Match(teams.get(6).Id,teams.get(1).Id,borgArab.id,maarouf.Id, LocalDateTime.of(2023,4,9,20,0));
        matches[45]=new Match(teams.get(3).Id,teams.get(2).Id,cairo.id,ibrahim.Id, LocalDateTime.of(2023,4,10,20,0));
        matches[46]=new Match(teams.get(5).Id,teams.get(4).Id,elSalam.id,elbana.Id, LocalDateTime.of(2023,4,11,20,0));
        matches[47]=new Match(teams.get(3).Id,teams.get(0).Id,petrosport.id,maarouf.Id, LocalDateTime.of(2023,4,12,20,0));
        matches[48]=new Match(teams.get(5).Id,teams.get(0).Id,borgArab.id,nagy.Id, LocalDateTime.of(2023,4,19,20,0));
        matches[49]=new Match(teams.get(5).Id,teams.get(1).Id,cairo.id,ibrahim.Id, LocalDateTime.of(2023,4,20,20,0));
        matches[50]=new Match(teams.get(7).Id,teams.get(1).Id,borgArab.id,maarouf.Id, LocalDateTime.of(2023,4,21,20,0));
        matches[51]=new Match(teams.get(6).Id,teams.get(2).Id,elSalam.id,nagy.Id, LocalDateTime.of(2023,4,22,22,0));
        matches[52]=new Match(teams.get(7).Id,teams.get(2).Id,petrosport.id,elbana.Id, LocalDateTime.of(2023,5,1,18,0));
        matches[53]=new Match(teams.get(4).Id,teams.get(3).Id,cairo.id,ibrahim.Id, LocalDateTime.of(2023,5,2,20,0));
        matches[54]=new Match(teams.get(6).Id,teams.get(4).Id,borgArab.id,nagy.Id, LocalDateTime.of(2023,5,3,20,0));
        matches[55]=new Match(teams.get(7).Id,teams.get(4).Id,elSalam.id,maarouf.Id, LocalDateTime.of(2023,5,4,20,0));



        ApplicationRepository.getRepository().putReferee(ibrahim);
        ApplicationRepository.getRepository().putReferee(ghandour);
        ApplicationRepository.getRepository().putReferee(elbana);
        ApplicationRepository.getRepository().putReferee(nagy);
        ApplicationRepository.getRepository().putReferee(maarouf);

    }

    private static Team insertTeam(String teamName, Player[] players, int captainIndex) {
        Team team = new Team(teamName, null);
        ApplicationRepository.getRepository().putTeam(team);

        for (Player p : players) {
            ApplicationRepository.getRepository().putPlayer(p);
            team.addPlayer(p.Id);
            p.setTeam(team);
        }

        team.setCaptain(players[captainIndex]);
        return team;
    }
}

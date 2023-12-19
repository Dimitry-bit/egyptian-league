package com.github.egyptian_league;

import com.github.egyptian_league.Models.*;

import java.time.LocalDate;
import java.util.UUID;

public class Create {
    public void create() {
        Team zamalek = new Team("Zamalek", null);
        Player shikabala = new Player("Shikabala", zamalek.Id, LocalDate.of(1986, 3, 5), Position.FORWARD, 10);
        zamalek.setCaptainId(shikabala.Id);
        Player zizo = new Player("Zizo", zamalek.Id, LocalDate.of(1996, 1, 10), Position.FORWARD, 25);
        Player jaziri = new Player("Jaziri", zamalek.Id, LocalDate.of(1993, 2, 12), Position.FORWARD, 30);
        Player obama = new Player("Obama", zamalek.Id, LocalDate.of(1995, 5, 26), Position.MIDFIELD, 14);
        Player donga = new Player("Donga", zamalek.Id, LocalDate.of(1996, 4, 6), Position.MIDFIELD, 8);
        Player Roka = new Player("Roka", zamalek.Id, LocalDate.of(1993, 7, 10), Position.MIDFIELD, 12);
        Player shifo = new Player("Shifo", zamalek.Id, LocalDate.of(1985, 7, 1), Position.DEFENDER, 29);
        Player wensh = new Player("Wensh", zamalek.Id, LocalDate.of(1995, 6, 1), Position.DEFENDER, 28);
        Player mathlouthi = new Player("Mathlouthi", zamalek.Id, LocalDate.of(1992, 7, 25), Position.DEFENDER, 24);
        Player fatouh = new Player("Fatouh", zamalek.Id, LocalDate.of(1998, 3, 21), Position.DEFENDER, 13);
        Player awad = new Player("Awad", zamalek.Id, LocalDate.of(1991, 9, 30), Position.GOALKEEPER, 1);
        ////
        Team ahly = new Team("Ahly", null);
        Player shenawy = new Player("Shenawy", ahly.Id, LocalDate.of(1988, 12, 18), Position.GOALKEEPER, 1);
        ahly.setCaptainId(shenawy.Id);
        Player maaloul = new Player("Maaloul", ahly.Id, LocalDate.of(1990, 1, 1), Position.DEFENDER, 21);
        Player tawfik = new Player("Tawfik", ahly.Id, LocalDate.of(1997, 11, 8), Position.DEFENDER, 8);
        Player hany = new Player("Hany", ahly.Id, LocalDate.of(1996, 1, 25), Position.DEFENDER, 30);
        Player yasser = new Player("Yasser", ahly.Id, LocalDate.of(1993, 2, 10), Position.DEFENDER, 6);
        Player ashour = new Player("Ashour", ahly.Id, LocalDate.of(1998, 2, 20), Position.MIDFIELD, 22);
        Player afsha = new Player("Afsha", ahly.Id, LocalDate.of(1996, 3, 6), Position.MIDFIELD, 19);
        Player dieng = new Player("Dieng", ahly.Id, LocalDate.of(1997, 10, 16), Position.MIDFIELD, 23);
        Player tau = new Player("Tau", ahly.Id, LocalDate.of(1994, 5, 13), Position.FORWARD, 10);
        Player shahat = new Player("Shahat", ahly.Id, LocalDate.of(1992, 6, 21), Position.FORWARD, 14);
        Player kahraba = new Player("Kahraba", ahly.Id, LocalDate.of(1994, 4, 13), Position.FORWARD, 7);
        ////
        Team pyramids = new Team("Pyramids", null);
        Player ekramy = new Player("Ekramy", pyramids.Id, LocalDate.of(1983, 7, 10), Position.GOALKEEPER, 22);
        Player samy = new Player("Samy", pyramids.Id, LocalDate.of(1992, 5, 31), Position.DEFENDER, 4);
        Player gabr = new Player("Gabr", pyramids.Id, LocalDate.of(1989, 1, 9), Position.DEFENDER, 5);
        Player chibi = new Player("Chibi", pyramids.Id, LocalDate.of(1993, 1, 21), Position.DEFENDER, 15);
        Player hamdy = new Player("Hamdy", pyramids.Id, LocalDate.of(1995, 3, 15), Position.DEFENDER, 21);
        Player toure = new Player("Toure", pyramids.Id, LocalDate.of(1994, 8, 4), Position.MIDFIELD, 7);
        Player karti = new Player("Karti", pyramids.Id, LocalDate.of(1997, 7, 23), Position.MIDFIELD, 18);
        Player abdullah = new Player("Abdullah", pyramids.Id, LocalDate.of(1985, 7, 13), Position.MIDFIELD, 19);
        Player ramadan = new Player("Ramadan", pyramids.Id, LocalDate.of(1997, 1, 23), Position.FORWARD, 10);
        Player adel = new Player("Adel", pyramids.Id, LocalDate.of(2001, 5, 1), Position.FORWARD, 30);
        Player mayele = new Player("Mayele", pyramids.Id, LocalDate.of(1994, 6, 24), Position.FORWARD, 9);
        pyramids.setCaptainId(abdullah.Id);
        ////
        Team ismaily = new Team("Ismaily", null);
        Player fawzi = new Player("fawzi", ismaily.Id, LocalDate.of(1993, 7, 10), Position.GOALKEEPER, 1);
        Player nagguez = new Player("Nagguez", ismaily.Id, LocalDate.of(1992, 10, 28), Position.DEFENDER, 21);
        Player arafat = new Player("Arafat", ismaily.Id, LocalDate.of(1993, 5, 26), Position.DEFENDER, 24);
        Player mohammed = new Player("Mohammed", ismaily.Id, LocalDate.of(2002, 9, 1), Position.DEFENDER, 26);
        Player desoky = new Player("Desoky", ismaily.Id, LocalDate.of(1997, 12, 18), Position.DEFENDER, 3);
        Player gomaa = new Player("Gomaa", ismaily.Id, LocalDate.of(1993, 8, 1), Position.MIDFIELD, 14);
        Player bayoumi = new Player("Bayoumi", ismaily.Id, LocalDate.of(2000, 4, 7), Position.MIDFIELD, 8);
        Player makhlof = new Player("Makhlof", ismaily.Id, LocalDate.of(1998, 1, 1), Position.MIDFIELD, 6);
        Player shabrawy = new Player("Shabrawy",ismaily.Id, LocalDate.of(1994, 12, 15), Position.FORWARD, 7);
        Player morsi = new Player("Morsi", ismaily.Id, LocalDate.of(1992, 1, 1), Position.FORWARD, 17);
        Player wagdy = new Player("Wagdy", ismaily.Id, LocalDate.of(2002, 3, 4), Position.FORWARD, 23);
        pyramids.setCaptainId(fawzi.Id);
        ////
        Team future = new Team("Future", null);
        Player genish = new Player("Genish", future.Id, LocalDate.of(1987, 7, 9), Position.GOALKEEPER, 16);
        Player basem = new Player("Basem", future.Id, LocalDate.of(1988, 10, 27), Position.DEFENDER, 2);
        Player rizk = new Player("Rizk", future.Id, LocalDate.of(1990, 1, 1), Position.DEFENDER, 4);
        Player fil = new Player("Fil", future.Id, LocalDate.of(1992, 12, 13), Position.DEFENDER, 6);
        Player taha = new Player("Taha", future.Id, LocalDate.of(1991, 9, 4), Position.DEFENDER, 11);
        Player nasser = new Player("Nasser", future.Id, LocalDate.of(1997, 2, 8), Position.MIDFIELD, 10);
        Player ghanam= new Player("Ghanam", future.Id, LocalDate.of(1997, 3, 12), Position.MIDFIELD, 27);
        Player zaazaa = new Player("Zaazaa", future.Id, LocalDate.of(2001, 6, 23), Position.MIDFIELD, 37);
        Player barry= new Player("barry", future.Id, LocalDate.of(1991, 9, 27), Position.FORWARD, 3);
        Player atef= new Player("Atef", future.Id, LocalDate.of(1998, 3, 21), Position.FORWARD, 30);
        Player balaha = new Player("Balaha", future.Id, LocalDate.of(1996, 6, 21), Position.FORWARD, 8);
        pyramids.setCaptainId(genish.Id);
        ////

        Stadium cairo = new Stadium("Cairo Stadium", "Nasr City");
        Stadium borgArab = new Stadium("Borg El Arab Stadium", "Borg El Arab");
        Stadium petrosport = new Stadium("Petrosport Stadium", "New Cairo");

        Referee ibrahim = new Referee("Ibrahim");
        Referee ghandour = new Referee("Ghandour");
        Referee elbana = new Referee("El Bana");
        Referee nagy = new Referee("nagy");
        Referee maarouf = new Referee("Maarouf");
    }
}

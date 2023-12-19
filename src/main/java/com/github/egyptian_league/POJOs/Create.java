package com.github.egyptian_league.POJOs;

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

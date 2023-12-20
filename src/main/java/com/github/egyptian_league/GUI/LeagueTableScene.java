package com.github.egyptian_league.GUI;
import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.League;
import com.github.egyptian_league.Models.Match;
import com.github.egyptian_league.POJOs.MatchPojo;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;


public class LeagueTableScene extends TableScene<League> {
 private static LeagueTableScene League_Table =new LeagueTableScene();
 private LeagueTableScene  (){
 }
 public static LeagueTableScene getLeage_table_scene() {
    return League_Table;
 }
 @Override
 void addRow() {
  String name=textFields.get("League name").getText();
  String years=textFields.get("year").getText();
  try {
   int Years = Integer.parseInt(years);
   clearInput();
  } catch (Exception e) {
   System.err.printf("Invalid data, %s", e.getMessage());
   e.printStackTrace();
  }
 }

 private void clearInput() {
  textFields.get("League name").clear();
  textFields.get("year").clear();
 }


 @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
  createTextField("League name", 100, 30, inputHBox, null);
  createTextField("year", 100, 30, inputHBox, null);
  createTableColumn("League",String.class,tableView);
  createTableColumn("Year",int.class,tableView);
  addInsertButton("insert");
  addDeleteButton("delete");

    }
 private void seedLeagueTableView() {
  Iterator<League> Leagueiter = ApplicationRepository.getRepository().getLeaguesIterator();
  while (Leagueiter.hasNext()) tableView.getItems().add(new League(Leagueiter.next().getLeagueName(),Leagueiter.next().getYear()));
 }
}

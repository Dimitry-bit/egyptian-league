package com.github.egyptian_league.GUI;
import com.github.egyptian_league.Models.League;


public class LeagueTableScene extends TableScene<League> {
 private static LeagueTableScene League_Table =new LeagueTableScene();
 private LeagueTableScene  (){
  addTextField("League name");
  addTextField("year");
  addColumn("Leaguename",String.class);
  addColumn("Year",int.class);
  addInsertButton("insert");
  addDeleteButton("delete");
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


}

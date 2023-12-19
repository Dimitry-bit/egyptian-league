package com.github.egyptian_league.GUI;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Player;
import com.github.egyptian_league.Models.Position;
import com.github.egyptian_league.Models.Team;
import com.github.egyptian_league.POJOs.PlayerPojo;

public class PlayerTableScene extends TableScene<PlayerPojo> {

    public PlayerTableScene() {
        addTextField("name");
        addTextField("team");
        addTextField("position");
        addTextField("number");

        addInsertButton("Insert");
        addDeleteButton("Delete");

        addColumn("Name", String.class);
        addColumn("TeamName", String.class);
        addColumn("age", Integer.class);
        addColumn("Position", Position.class);
        addColumn("ShirtNumber", Integer.class);
        addColumn("Rank", Integer.class);
    }

    @Override
    public void addRow() {
        String name = textFields.get("name").getText();
        String teamName = textFields.get("team").getText();
        String positionText = textFields.get("position").getText();
        String playerShirtNumber = textFields.get("number").getText();

        try {
            Position p = Position.valueOf(positionText);
            int n = Integer.parseInt(playerShirtNumber);

            if (!ApplicationRepository.getRepository().containsTeamName(teamName)) {
                // TODO: Show error
                clearInput();
                return;
            }

            // TODO: Add to repository
            Team[] teams = ApplicationRepository.getRepository().getTeamsByName(teamName);
//            Player player = new Player(name, teams[0].Id, p, n);

//            table.getItems().add(new PlayerPojo(player));
        } catch (Exception e) {
            // TODO: Recover from exceptions
            System.err.printf("Invalid data, %s", e.getMessage());
            e.printStackTrace();
        }
    }
}

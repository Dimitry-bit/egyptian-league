package com.github.egyptian_league.GUI;

import java.time.LocalDate;

import com.github.egyptian_league.ApplicationRepository;
import com.github.egyptian_league.Models.Player;
import com.github.egyptian_league.Models.Position;
import com.github.egyptian_league.Models.Team;
import com.github.egyptian_league.POJOs.PlayerPojo;

public class PlayerTableScene extends TableScene<PlayerPojo> {

    public PlayerTableScene() {
        addTextField("Name");
        addTextField("Team Name");
        addTextField("Position");
        addTextField("Shirt Number");

        addDate("Birthday");

        addInsertButton("Insert");
        addDeleteButton("Delete");

        addColumn("Name", String.class);
        addColumn("TeamName", String.class);
        addColumn("Birthday", LocalDate.class);
        addColumn("Age", Integer.class);
        addColumn("Position", Position.class);
        addColumn("ShirtNumber", Integer.class);
        addColumn("Rank", Integer.class);
    }

    @Override
    public void addRow() {
        String name = textFields.get("Name").getText();
        String teamName = textFields.get("Team Name").getText();
        String positionText = textFields.get("Position").getText();
        String playerShirtNumber = textFields.get("Shirt Number").getText();
        LocalDate birthday = datePickers.get("Birthday").getValue();

        try {
            Position position = Position.valueOf(positionText);
            int shirtNumber = Integer.parseInt(playerShirtNumber);

            if (!ApplicationRepository.getRepository().containsTeamName(teamName)) {
                // TODO: Show error
                return;
            }

            // TODO: Add to repository
            Team[] teams = ApplicationRepository.getRepository().getTeamsByName(teamName);
            Player player = new Player(name, teams[0].Id, birthday, position, shirtNumber);

            table.getItems().add(new PlayerPojo(player));
            clearInput();
        } catch (Exception e) {
            // TODO: Recover from exceptions
            System.err.printf("Invalid data, %s", e.getMessage());
            e.printStackTrace();
        }
    }
}

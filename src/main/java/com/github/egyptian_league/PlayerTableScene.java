package com.github.egyptian_league;

public class PlayerTableScene extends TableScene<PlayerPojo> {

    public PlayerTableScene() {
        addTextfield("name");
        addTextfield("team");
        addTextfield("position");
        addTextfield("number");

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
            Player player = new Player(name, teams[0].getUuid(), p, n);

            table.getItems().add(new PlayerPojo(player));
        } catch (Exception e) {
            // TODO: Recover from exceptions
            System.err.printf("Invalid data, %s", e.getMessage());
            e.printStackTrace();
        }
    }
}

package com.github.egyptian_league.GUI;

import com.github.egyptian_league.Models.Position;

import javafx.util.StringConverter;

public class PositionConverter extends StringConverter<Position> {

    @Override
    public String toString(Position object) {
        if (object == null) {
            return null;
        }

        return object.toString();
    }

    @Override
    public Position fromString(String string) {
        for (Position p : Position.values()) {
            if (p.toString().equals(string)) {
                return Position.valueOf(string);
            }
        }

        return null;
    }
}

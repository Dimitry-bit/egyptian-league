/*
 *
 * Copyright (c) 2023 Tony Medhat
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.egyptian_league.json.src;

import java.util.Objects;

public class JsonPrimitive extends JsonElement {
    public final Object value;

    public JsonPrimitive(Character c) {
        value = Objects.requireNonNull(c);
    }

    public JsonPrimitive(String s) {
        value = Objects.requireNonNull(s);
    }

    public JsonPrimitive(Boolean b) {
        value = Objects.requireNonNull(b);
    }

    public JsonPrimitive(Number n) {
        value = Objects.requireNonNull(n);
    }

    public boolean isBoolean() {
        return (value instanceof Boolean);
    }

    public boolean isNumber() {
        return (value instanceof Number);
    }

    public boolean isChar() {
        return (value instanceof Character);
    }

    public boolean isString() {
        return (value instanceof String);
    }

    public String getAsString() {
        return (isString() ? (String) value : value.toString());
    }

    public Number getAsNumber() {
        if (!isNumber()) {
            throw new JsonException("Primitive is not a number");
        }

        return ((Number) value);
    }

    public Boolean getAsBoolean() {
        return (isBoolean() ? (Boolean) value : Boolean.parseBoolean(getAsString()));
    }

    public Character getAsCharacter() {
        if (isChar()) {
            return (Character) (value);
        }

        String s = getAsString();
        if (!s.isEmpty()) {
            return s.charAt(0);
        }

        throw new JsonException("String is empty");
    }

    public static void main(String[] args) {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;

        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        return (value == ((JsonPrimitive) obj).value);
    }

    @Override
    public String toString() {
        return getAsString();
    }
}

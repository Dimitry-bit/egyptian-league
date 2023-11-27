/*
 *
 * Copyright (c) 2023 Tony Medhat
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
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

package com.github.egyptian_league.json;

public class JsonToken {

    public static final char LEFT_CURLY_BRACKET = '{';
    public static final char RIGHT_CURLY_BRACKET = '}';
    public static final char LEFT_SQUARE_BRACKET = '[';
    public static final char RIGHT_SQUARE_BRACKET = ']';
    public static final char COMMA = ',';
    public static final char COLON = ':';
    public static final char QUOTE = '"';
    public static final String LITERAL_TRUE = "true";
    public static final String LITERAL_FALSE = "false";
    public static final String LITERAL_NULL = "null";

    public final JsonTokenType type;
    public final String value;

    public JsonToken(String value, JsonTokenType type) {
        this.value = value;
        this.type = type;
    }

    public char getFirstChar() {
        return value.charAt(0);
    }

    @Override
    public String toString() {
        return value;
    }
}
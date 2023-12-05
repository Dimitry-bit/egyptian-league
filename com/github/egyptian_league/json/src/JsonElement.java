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

public abstract class JsonElement {
    public boolean isJsonArray() {
        return (this instanceof JsonArray);
    }

    public boolean isJsonObject() {
        return (this instanceof JsonObject);
    }

    public boolean isJsonPrimitive() {
        return (this instanceof JsonPrimitive);
    }

    public boolean isJsonNull() {
        return (this instanceof JsonNull);
    }

    public JsonObject getAsJsonObject() {
        if (isJsonObject()) {
            return (JsonObject) this;
        }

        throw new JsonException("Not a Json Object");
    }

    public JsonArray getAsJsonArray() {
        if (isJsonArray()) {
            return (JsonArray) this;
        }

        throw new JsonException("Not a Json Array");
    }

    public JsonPrimitive getAsJsonPrimitive() {
        if (isJsonPrimitive()) {
            return (JsonPrimitive) this;
        }

        throw new JsonException("Not a Json Primitive");
    }
}

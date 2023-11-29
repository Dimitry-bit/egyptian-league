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

package com.github.egyptian_league.json;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class JsonObject extends JsonElement {
    private HashMap<String, JsonElement> members;

    public JsonObject() {
        super();
        members = new HashMap<>();
    }

    public JsonObject(String source) {
        this();
        fromJson(source);
    }

    public int size() {
        return members.size();
    }

    public void clear() {
        members.clear();
    }

    public Object clone() {
        return members.clone();
    }

    public boolean isEmpty() {
        return members.isEmpty();
    }

    public Set<String> keySet() {
        return members.keySet();
    }

    public Collection<JsonElement> values() {
        return members.values();
    }

    public boolean containsKey(String key) {
        return members.containsKey(key);
    }

    public boolean containsValues(JsonElement element) {
        return members.containsValue(element);
    }

    public JsonElement get(String key) {
        return members.get(key);
    }

    public JsonElement put(String key, JsonElement element) {
        return members.put(key, element);
    }

    public JsonElement remove(String key) {
        return members.remove(key);
    }

    public boolean remove(String key, JsonElement element) {
        return members.remove(key, element);
    }

    public JsonObject fromJson(String source) {
        JsonLexer lexer = new JsonLexer(source);
        JsonObject t = JsonParser.parseObject(lexer);
        members = t.members;
        return this;
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        sb.append("{ ");
        for (String key : keySet()) {
            if (i != 0) {
                sb.append(", ");
            }

            sb.append("\"%s\" : ".formatted(key));

            JsonElement element = get(key);
            if (element.isJsonArray()) {
                sb.append(element.getAsJsonArray().toJson());
            } else if (element.isJsonObject()) {
                sb.append(element.getAsJsonObject().toJson());
            } else if (element.isJsonPrimitive()) {
                sb.append(element.getAsJsonPrimitive().getAsString());
            } else {
                sb.append(element.toString());
            }

            i++;
        }
        sb.append(" }");

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder json = new StringBuilder();

        while (in.hasNextLine()) {
            json.append(in.nextLine());
            json.append(System.lineSeparator());
        }
        in.close();

        JsonObject j = new JsonObject(json.toString());
        System.out.println(j.toJson());
    }
}
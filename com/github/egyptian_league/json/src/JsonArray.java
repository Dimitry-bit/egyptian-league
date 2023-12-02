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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public class JsonArray extends JsonElement implements Iterable<JsonElement> {

    private ArrayList<JsonElement> elements;

    public JsonArray() {
        super();
        elements = new ArrayList<>();
    }

    public JsonArray(String source) {
        this();
        fromJson(source);
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void clear() {
        elements.clear();
    }

    public boolean add(JsonElement element) {
        return elements.add(element);
    }

    public boolean addAll(Collection<? extends JsonElement> elements) {
        return this.elements.addAll(elements);
    }

    public boolean contains(JsonElement element) {
        return elements.contains(element);
    }

    public boolean containsAll(Collection<?> elements) {
        return this.elements.containsAll(elements);
    }

    public JsonElement get(int index) {
        return elements.get(index);
    }

    public JsonElement remove(int index) {
        return elements.remove(index);
    }

    public boolean removeAll(Collection<?> elements) {
        return this.elements.removeAll(elements);
    }

    public boolean removeIf(Predicate<? super JsonElement> elements) {
        return this.elements.removeIf(elements);
    }

    public Collection<JsonElement> getElements() {
        return elements;
    }

    public JsonArray fromJson(String source) {
        JsonLexer lexer = new JsonLexer(source);
        JsonArray t = JsonParser.parseArray(lexer);
        t.elements = elements;
        return this;
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");
        for (int i = 0; i < size(); ++i) {
            JsonElement element = get(i);
            if (i != 0) {
                sb.append(", ");
            }

            if (element.isJsonArray()) {
                sb.append(element.getAsJsonArray().toJson());
            } else if (element.isJsonObject()) {
                sb.append(element.getAsJsonObject().toJson());
            } else if (element.isJsonPrimitive()) {
                sb.append(element.getAsJsonPrimitive().getAsString());
            } else {
                sb.append(element.toString());
            }
        }
        sb.append(" ]");

        return sb.toString();

    }

    @Override
    public Iterator<JsonElement> iterator() {
        return elements.iterator();
    }
}
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

package com.github.egyptian_league.json.Converters;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Queue;

import com.github.egyptian_league.json.*;

public class JsonConverterLocalDateTime extends JsonConverter<LocalDateTime> {

    @Override
    public Type getMyType() {
        return LocalDateTime.class;
    }

    @Override
    public void serialize(Queue<JsonToken> tokens, LocalDateTime value, JsonSerializerOptions options) {
        tokens.add(new JsonToken('"' + value.toString() + '"', JsonTokenType.STRING));
    }

    @Override
    public LocalDateTime deserialize(JsonElement element, Type typeToConvert, JsonSerializerOptions options) {
        if (element.isJsonNull()) {
            return null;
        }

        if (!element.isJsonPrimitive() || !element.getAsJsonPrimitive().isString()) {
            throw new IllegalArgumentException("JsonElement is not a '%s'".formatted(getMyType().getTypeName()));
        }

        return LocalDateTime.parse(element.getAsJsonPrimitive().getAsString());
    }

}

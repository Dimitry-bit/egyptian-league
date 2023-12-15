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

import java.util.Queue;

import com.github.egyptian_league.json.JsonConverter;
import com.github.egyptian_league.json.JsonElement;
import com.github.egyptian_league.json.JsonSerializerOptions;
import com.github.egyptian_league.json.JsonToken;
import com.github.egyptian_league.json.JsonTokenType;
import com.github.egyptian_league.json.TypeToken;

public class JsonConverterEnum extends JsonConverter<Enum<?>> {

    @Override
    public TypeToken<Enum<?>> getMyType() {
        return new TypeToken<>() {
        };
    }

    @Override
    public boolean canConvert(TypeToken<?> typeToConvert) {
        return typeToConvert.getRawType().isEnum();
    }

    @Override
    public void serialize(Queue<JsonToken> tokens, Object value, JsonSerializerOptions options) {
        tokens.add(new JsonToken(((Integer) ((Enum<?>) value).ordinal()).toString(), JsonTokenType.NUMBER));
    }

    @Override
    public Object deserialize(JsonElement element, TypeToken<?> typeToConvert, JsonSerializerOptions options) {
        if (!element.isJsonPrimitive() || !element.getAsJsonPrimitive().isNumber()) {
            throw new IllegalArgumentException(
                    "JsonElement is not a '%s'".formatted(getMyType().getType().getTypeName()));
        }

        Class<?> typeClass = (Class<?>) typeToConvert.getType();
        return typeClass.getEnumConstants()[element.getAsJsonPrimitive().getAsNumber().intValue()];
    }
}

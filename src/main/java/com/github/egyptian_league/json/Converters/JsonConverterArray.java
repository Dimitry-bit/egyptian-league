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

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Queue;

import com.github.egyptian_league.json.*;

public class JsonConverterArray extends JsonConverter {

    @Override
    public Type getMyType() {
        return Object[].class;
    }

    @Override
    public boolean canConvert(Type typeToConvert) {
        if (typeToConvert == null) {
            return false;
        }

        if (typeToConvert instanceof Class<?>) {
            return ((Class<?>) typeToConvert).isArray();
        }

        return false;
    }

    @Override
    public void serialize(Queue tokens, Object value, JsonSerializerOptions options) {
        int length = Array.getLength(value);
        tokens.add(new JsonToken("[", JsonTokenType.ARRAY_START));
        for (int i = 0; i < length; ++i) {
            if (i != 0) {
                tokens.add(new JsonToken(",", JsonTokenType.COMMA));
            }

            Object v = Array.get(value, i);

            if (v == null) {
                continue;
            }

            if (!options.hasConverter(v.getClass())) {
                throw new JsonException("'%s' can not serialize".formatted(v.getClass().getName()));
            }

            JsonConverter converter = options.getConverter(v.getClass());
            converter.serialize(tokens, v, options);
        }
        tokens.add(new JsonToken("]", JsonTokenType.ARRAY_END));
    }

    @Override
    public Object deserialize(JsonElement element, Type typeToConvert, JsonSerializerOptions options) {
        Class<?> typeClass = (Class<?>) typeToConvert;

        if (!typeClass.isArray()) {
            throw new IllegalArgumentException("'%s' type is not an array".formatted(typeClass.getName()));
        }

        if (!element.isJsonArray()) {
            throw new IllegalArgumentException("JsonElement is not a '%s'".formatted(getMyType().getTypeName()));
        }

        JsonArray jsonArray = element.getAsJsonArray();
        Object array = Array.newInstance(typeClass.getComponentType(), jsonArray.size());
        int length = Array.getLength(array);
        Class<?> componentType = typeClass.getComponentType();

        for (int i = 0; i < length; ++i) {
            JsonElement valueElement = jsonArray.get(i);
            Object v = null;

            if (!options.hasConverter(componentType)) {
                throw new JsonException("'%s' can not deserialize".formatted(componentType.getName()));
            }

            JsonConverter converter = options.getConverter(componentType);
            v = converter.deserialize(valueElement, componentType, options);
            Array.set(array, i, v);
        }

        return array;
    }
}

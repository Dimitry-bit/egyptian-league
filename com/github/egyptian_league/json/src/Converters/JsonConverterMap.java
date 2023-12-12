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

package com.github.egyptian_league.json.src.Converters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;

import com.github.egyptian_league.json.src.*;

public class JsonConverterMap extends JsonConverter<Map> {

    @Override
    public Type getMyType() {
        return Map.class;
    }

    @Override
    public boolean canConvert(Type typeToConvert) {
        if (typeToConvert == null) {
            return false;
        }

        if (typeToConvert instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) typeToConvert;
            if (parameterizedType.getRawType() instanceof Class<?>) {
                Class<?> rawTypeClass = (Class<?>) parameterizedType.getRawType();
                return Map.class.isAssignableFrom(rawTypeClass);
            }

            return false;
        } else if (typeToConvert instanceof Class<?>) {
            return Map.class.isAssignableFrom((Class<?>) typeToConvert);
        }

        return false;

    }

    @Override
    public void serialize(Queue<JsonToken> tokens, Map value, JsonSerializerOptions options) {
        boolean printComma = false;
        Map<?, ?> m = value;

        tokens.add(new JsonToken("{", JsonTokenType.OBJECT_START));
        for (Entry<?, ?> e : m.entrySet()) {
            if (printComma) {
                tokens.add(new JsonToken(",", JsonTokenType.COMMA));
            }

            tokens.add(new JsonToken('"' + e.getKey().toString() + '"',
                    JsonTokenType.STRING));
            tokens.add(new JsonToken(":", JsonTokenType.COLON));

            if (!options.hasConverter((Type) e.getValue().getClass())) {
                throw new JsonException("'%s' can not serialize".formatted(e.getValue().getClass().getName()));
            }

            JsonConverter converter = options.getConverter((Type) e.getValue().getClass());
            converter.serialize(tokens, e.getValue(), options);

            printComma = true;
        }
        tokens.add(new JsonToken("}", JsonTokenType.OBJECT_END));
    }

    @Override
    public Map deserialize(JsonElement element, Type typeToConvert, JsonSerializerOptions options) {
        if (!canConvert(typeToConvert)) {
            throw new IllegalArgumentException("'%s' type is not a collection".formatted(typeToConvert.getTypeName()));
        }

        if (element.isJsonNull()) {
            return null;
        }

        if (!element.isJsonObject()) {
            throw new IllegalArgumentException(
                    "'%s' JsonElement is not a JsonObject".formatted(element.getClass().getName()));
        }

        ParameterizedType parameterizedType = (ParameterizedType) typeToConvert;
        Class<?> mapType = (Class<?>) parameterizedType.getRawType();
        JsonObject j = element.getAsJsonObject();
        Type keyType = parameterizedType.getActualTypeArguments()[0];
        Type valueType = parameterizedType.getActualTypeArguments()[1];

        if (!(keyType instanceof Class) || !((Class<?>) keyType).isAssignableFrom(String.class)) {
            throw new JsonException("'%s' key is not supported".formatted(keyType.getTypeName()));
        }

        try {
            Map<Object, Object> map = (Map<Object, Object>) mapType.getConstructor().newInstance();

            for (String key : j.keySet()) {
                JsonElement valueElement = j.get(key);
                Object v = null;

                if (!options.hasConverter(valueType)) {
                    throw new JsonException("'%s' can not deserialize".formatted(valueType.getTypeName()));
                }

                if (valueType instanceof ParameterizedType) {
                    if (!options.hasConverter(((ParameterizedType) valueType).getActualTypeArguments()[0])) {
                        throw new JsonException("'%s' can not deserialize".formatted(valueType.getTypeName()));
                    }

                    JsonConverter converter = options.getConverter(valueType);
                    v = converter.deserialize(valueElement, ((ParameterizedType) valueType), options);
                } else if (valueType instanceof Class) {
                    JsonConverter converter = options.getConverter(valueType);
                    v = converter.deserialize(valueElement, valueType, options);
                }

                map.put(key, v);
            }

            return map;
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.err.println("deserialization: " + e.getMessage());
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new UnsupportedOperationException(
                    "User defined constructor instantiation is not supported, " + e.getMessage());
        } catch (NoSuchMethodException e) {
            throw new UnsupportedOperationException("Nested classes serialization is not supported, " + e.getMessage());
        }

        return null;

    }
}

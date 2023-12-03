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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.Set;

import com.github.egyptian_league.json.src.*;

public class JsonConverterObject extends JsonConverter<Object> {

    @Override
    public Type getMyType() {
        return Object.class;
    }

    @Override
    public boolean canConvert(Type typeToConvert) {
        if (typeToConvert == null) {
            return false;
        }

        if (typeToConvert instanceof Class<?>) {
            Class<?> typeClass = (Class<?>) typeToConvert;
            return !typeClass.isMemberClass() && !typeClass.isAnonymousClass()
                    && !typeClass.isLocalClass() && !typeClass.isInterface();
        }

        return false;
    }

    @Override
    public void serialize(Queue<JsonToken> tokens, Object value, JsonSerializerOptions options) {
        Set<Field> fields = new LinkedHashSet<>();
        Class<?> objectClass = value.getClass();

        Collections.addAll(fields, objectClass.getFields());
        Collections.addAll(fields, objectClass.getDeclaredFields());

        try {
            boolean isPrintComma = false;
            tokens.add(new JsonToken("{", JsonTokenType.OBJECT_START));
            for (Field f : fields) {
                if (isPrintComma) {
                    tokens.add(new JsonToken(",", JsonTokenType.COMMA));
                }

                tokens.add(new JsonToken('"' + f.getName() + '"', JsonTokenType.STRING));
                tokens.add(new JsonToken(":", JsonTokenType.COLON));

                f.setAccessible(true);
                Object v = f.get(value);
                Type fieldType = f.getGenericType();

                if (v == null) {
                    tokens.add(new JsonToken("null", JsonTokenType.NULL));
                } else {
                    if (!options.hasConverter(fieldType)) {
                        throw new JsonException("'%s' Can not serialize".formatted(v.getClass().getName()));
                    }

                    JsonConverter converter = options.getConverter(fieldType);
                    converter.serialize(tokens, v, options);
                }

                isPrintComma = true;
            }
            tokens.add(new JsonToken("}", JsonTokenType.OBJECT_END));
        } catch (IllegalAccessException e) {
            System.err.println("serialization: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Object deserialize(JsonElement element, Type typeToConvert, JsonSerializerOptions options) {
        if (element.isJsonNull()) {
            return null;
        }

        if (!element.isJsonObject()) {
            throw new IllegalArgumentException(
                    "'%s' JsonElement is not a JsonObject".formatted(element.getClass().getName()));
        }

        try {
            Class<?> type = (Class<?>) typeToConvert;
            type.getConstructor().setAccessible(true);
            Object o = type.getConstructor().newInstance();

            JsonObject jsonObject = element.getAsJsonObject();
            Set<Field> fields = new LinkedHashSet<>();

            Collections.addAll(fields, type.getFields());
            Collections.addAll(fields, type.getDeclaredFields());

            for (Field field : fields) {
                String key = field.getName();
                Type fieldType = field.getGenericType();
                Object value = null;
                JsonElement valueElement = null;

                if (!jsonObject.containsKey(key)) {
                    continue;
                }

                if (!options.hasConverter(fieldType)) {
                    throw new JsonException("'%s' can not deserialize".formatted(fieldType.getTypeName()));
                }

                valueElement = jsonObject.get(key);
                JsonConverter valueConverter = options.getConverter(fieldType);
                value = valueConverter.deserialize(valueElement, fieldType, options);

                field.setAccessible(true);
                field.set(o, value);
            }

            return o;
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

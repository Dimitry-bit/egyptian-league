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
import java.util.Collection;
import java.util.Queue;

import com.github.egyptian_league.json.src.*;

public class JsonConverterCollection extends JsonConverter {

    @Override
    public Type getMyType() {
        return Collection.class;
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
                return Collection.class.isAssignableFrom(rawTypeClass);
            }

            return false;
        } else if (typeToConvert instanceof Class<?>) {
            return Collection.class.isAssignableFrom((Class<?>) typeToConvert);
        }

        return false;
    }

    @Override
    public void serialize(Queue tokens, Object value, JsonSerializerOptions options) {
        boolean printComma = false;
        tokens.add(new JsonToken("[", JsonTokenType.ARRAY_START));
        Collection<?> c = (Collection<?>) value;
        for (Object v : c) {
            if (printComma) {
                tokens.add(new JsonToken(",", JsonTokenType.COMMA));
            }

            if (!options.hasConverter((Type) v.getClass())) {
                throw new JsonException("'%s' can not serialize".formatted(v.getClass().getName()));
            }

            JsonConverter converter = options.getConverter((Type) v.getClass());
            converter.serialize(tokens, v, options);
            printComma = true;
        }
        tokens.add(new JsonToken("]", JsonTokenType.ARRAY_END));
    }

    @Override
    public Object deserialize(JsonElement element, Type typeToConvert, JsonSerializerOptions options) {
        if (!canConvert(typeToConvert)) {
            throw new IllegalArgumentException("'%s' type is not a collection".formatted(typeToConvert.getTypeName()));
        }

        if (element.isJsonNull()) {
            return null;
        }

        if (!element.isJsonArray()) {
            throw new IllegalArgumentException(
                    "'%s' JsonElement is not a JsonArray".formatted(element.getClass().getName()));
        }

        ParameterizedType parameterizedType = (ParameterizedType) typeToConvert;
        Class<?> collectionType = (Class<?>) parameterizedType.getRawType();
        JsonArray j = element.getAsJsonArray();
        Type componentType = parameterizedType.getActualTypeArguments()[0];

        try {
            Collection<Object> collection = (Collection<Object>) collectionType.getConstructor().newInstance();

            for (int i = 0; i < j.size(); ++i) {
                JsonElement valueElement = j.get(i);
                Object v = null;

                if (!options.hasConverter(componentType)) {
                    throw new JsonException("'%s' can not deserialize".formatted(componentType.getTypeName()));
                }

                if (componentType instanceof ParameterizedType) {
                    if (!options.hasConverter(((ParameterizedType) componentType).getActualTypeArguments()[0])) {
                        throw new JsonException("'%s' can not deserialize".formatted(componentType.getTypeName()));
                    }

                    JsonConverter converter = options.getConverter(componentType);
                    v = converter.deserialize(valueElement, ((ParameterizedType) componentType), options);
                } else if (componentType instanceof Class) {
                    JsonConverter converter = options.getConverter(componentType);
                    v = converter.deserialize(valueElement, componentType, options);
                }

                collection.add(v);
            }

            return collectionType.cast(collection);
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

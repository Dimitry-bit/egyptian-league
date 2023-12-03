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

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import com.github.egyptian_league.json.src.Converters.*;

public class JsonSerializerOptions {
    public static final JsonSerializerOptions defaultOptions = new JsonSerializerOptions();
    private static final Queue<JsonConverter> converters = generateConverters();
    private static final Hashtable<Type, JsonConverter> mappedConverters = mapConverters();

    public int MaxDepth;
    public int tabWidth;
    public boolean WriteIndented;

    public JsonSerializerOptions() {
        MaxDepth = 65;
        tabWidth = 2;
        WriteIndented = false;
    }

    public boolean hasConverter(Type typeToConvert) {
        if (mappedConverters.containsKey(getGenericConverter(typeToConvert))) {
            return true;
        }

        for (JsonConverter converter : converters) {
            if (converter.canConvert(typeToConvert)) {
                return true;
            }
        }

        return false;
    }

    public JsonConverter getConverter(Type typeToConvert) {
        JsonConverter outConverter = null;

        if ((outConverter = mappedConverters.get(getGenericConverter(typeToConvert))) != null) {
            return outConverter;
        }

        for (JsonConverter converter : converters) {
            if (converter.canConvert(typeToConvert)) {
                return converter;
            }
        }

        throw new NoSuchElementException("No converter for '%s' is found".formatted(typeToConvert));
    }

    public boolean addConverter(Class<?> converterType, JsonConverter converter) {
        if (hasConverter(converterType)) {
            return false;
        }

        converters.add(converter);
        mappedConverters.put(converterType, converter);

        return true;
    }

    public void removeConverter(Class<?> converterType) {
        if (!hasConverter(converterType)) {
            return;
        }

        converters.add(getConverter(converterType));
        mappedConverters.remove(converterType);
    }

    private static Queue<JsonConverter> generateConverters() {
        Queue<JsonConverter> converters = new LinkedList<>();

        converters.add(new JsonConverterShort());
        converters.add(new JsonConverterInteger());
        converters.add(new JsonConverterLong());
        converters.add(new JsonConverterFloat());
        converters.add(new JsonConverterDouble());
        converters.add(new JsonConverterBoolean());
        converters.add(new JsonConverterCharacter());
        converters.add(new JsonConverterString());
        converters.add(new JsonConverterByte());
        converters.add(new JsonConverterEnum());
        converters.add(new JsonConverterLocalDateTime());
        converters.add(new JsonConverterUUID());
        converters.add(new JsonConverterArray());
        converters.add(new JsonConverterCollection());
        converters.add(new JsonConverterMap());

        // NOTE: Object converter must be last element (Fallback converter)
        converters.add(new JsonConverterObject());

        return converters;
    }

    private static Hashtable<Type, JsonConverter> mapConverters() {
        Hashtable<Type, JsonConverter> mappedConverters = new Hashtable<>();

        for (JsonConverter converter : converters) {
            mappedConverters.put(converter.getMyType(), converter);
        }

        return mappedConverters;
    }

    private static Type getGenericConverter(Type typeToConvert) {
        if (typeToConvert instanceof Class) {
            Class<?> typeClass = (Class<?>) typeToConvert;
            if (PrimitiveUtils.isPrimitiveOrWrapper(typeClass) && typeClass.isPrimitive()) {
                return PrimitiveUtils.wrap(typeClass);
            }

            if (typeClass.isArray()) {
                return Array.class;
            }
        }

        return typeToConvert;
    }
}

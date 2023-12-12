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

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import com.github.egyptian_league.json.Converters.*;

/**
 * JSON serialization options to modify the default serializer behavior. Also
 * allows for custom converters to be add.
 *
 * @author Tony Medhat
 */
public class JsonSerializerOptions {

    /**
     * Default options instance.
     */
    public static final JsonSerializerOptions DefaultOptions = new JsonSerializerOptions();

    private static final Queue<JsonConverter> defaultConverters = generateDefaultConverters();
    private static final Hashtable<Type, JsonConverter> mappedConverters = mapDefaultConverters();

    /**
     * Sets Max JSON recursion depth. (default: 65)
     */
    public int MaxDepth;

    /**
     * Sets JSON output tab width. (default: 2)
     */
    public int TabWidth;

    /**
     * Enables formatted JSON output. (default: false)
     */
    public boolean WriteIndented;

    private final Hashtable<Type, ArrayList<JsonConverter>> customConverters;

    /** Default constructor. */
    public JsonSerializerOptions() {
        MaxDepth = 65;
        TabWidth = 2;
        WriteIndented = false;
        customConverters = new Hashtable<>();
        generateCustomConverters();
    }

    /**
     * Returns true if a suitable converter is found
     *
     * @param typeToConvert type whose conversion is to be tested
     * @return true if a suitable converter is found
     */
    public boolean hasConverter(Type typeToConvert) {
        for (ArrayList<JsonConverter> converters : customConverters.values()) {
            for (JsonConverter converter : converters) {
                if (converter.canConvert(typeToConvert)) {
                    return true;
                }
            }
        }

        if (mappedConverters.containsKey(getGenericConverter(typeToConvert))) {
            return true;
        }

        for (JsonConverter converter : defaultConverters) {
            if (converter.canConvert(typeToConvert)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns {@code typeToConvert} converter.
     *
     * @param typeToConvert type whose associated converter is to be returned
     * @return {@code typeToConvert} converter
     */
    public JsonConverter getConverter(Type typeToConvert) {
        JsonConverter outConverter = null;

        for (ArrayList<JsonConverter> converters : customConverters.values()) {
            for (JsonConverter converter : converters) {
                if (converter.canConvert(typeToConvert)) {
                    return converter;
                }
            }
        }

        if ((outConverter = mappedConverters.get(getGenericConverter(typeToConvert))) != null) {
            return outConverter;
        }

        for (JsonConverter converter : defaultConverters) {
            if (converter.canConvert(typeToConvert)) {
                return converter;
            }
        }

        throw new NoSuchElementException("No converter for '%s' is found".formatted(typeToConvert));
    }

    /**
     * Maps a specific converter to a specific type.
     * <p>
     *
     * @param converter converter instance
     */
    public void addConverter(JsonConverter converter) {
        if (customConverters.containsKey(converter.getMyType())) {
            customConverters.get(converter.getMyType()).add(converter);
        } else {
            ArrayList<JsonConverter> converterList = new ArrayList<>();
            converterList.add(converter);
            customConverters.put(converter.getMyType(), converterList);
        }
    }

    /**
     * Removes the type and its corresponding converter. This method does nothing if
     * the key does not exist.
     *
     * @param converterType the type that needs to be removed
     */
    public void removeConverter(Class<?> converterType) {
        if (!hasConverter(converterType)) {
            return;
        }

        defaultConverters.add(getConverter(converterType));
        mappedConverters.remove(converterType);
    }

    private static Queue<JsonConverter> generateDefaultConverters() {
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

    private static Hashtable<Type, JsonConverter> mapDefaultConverters() {
        Hashtable<Type, JsonConverter> mappedConverters = new Hashtable<>();

        for (JsonConverter converter : defaultConverters) {
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

    private void generateCustomConverters() {
        addConverter(new JsonConverterUUIDMap());
    }
}

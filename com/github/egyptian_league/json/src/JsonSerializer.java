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

import java.util.LinkedList;
import java.util.Queue;

/*
 * Supported Types:
 *
 * Type Name                    | S | D
 * _____________________________|___|___
 * Primitives                   | T | T
 * Date                         | T | T
 * UUIDs                        | T | T
 * Arrays                       | T | T
 * Enums                        | T | T
 * Outer Classes                | T | T
 * ArrayList                    | T | T
 * LinkedList                   | T | T
 * Stack                        | T | T
 * Queue                        | T | T
 * ArrayDequeue                 | T | T
 * DelayQueue                   | T | T
 * PriorityBlockingQueue        | T | T
 * LinkedTransferQueue          | T | T
 * Vector                       | T | T
 * HashSet                      | T | T
 * LinkedHashSet                | T | T
 * TreeSet                      | T | T
 * Nested Collections           | T | T
 * Maps ( String Key! )         | T | T
 * ConcurrentHashMap            | T | T
 * ConcurrentSkipListMap        | T | T
 * HashMap                      | T | T
 * Hashtable                    | T | T
 * IdentityHashMap              | T | T
 * LinkedHashMap                | T | T
 * TreeMap                      | T | T
 * WeakHashMap                  | T | T
 * Nested Maps
 */

/*
 * NOTE(Tony): Some of these require a lot of work and will likely never be used in this project
 * Unsupported Types:
 *
 * Abstract Classes
 * Inner Classes
 * Anonymous Classes
 * Interfaces
 * Multidimensional Arrays
 */

public class JsonSerializer {

    private JsonSerializer() {
    }

    public static <T> T deserialize(String source, Class<T> type) {
        JsonObject jsonObject = new JsonObject(source);
        JsonSerializerOptions options = JsonSerializerOptions.defaultOptions;

        if (!options.hasConverter(Object.class)) {
            throw new JsonException("'%s' can not deserialize".formatted(type.getName()));
        }

        JsonConverter converter = options.getConverter(type);
        Object value = converter.deserialize(jsonObject, type, options);
        return type.cast(value);
    }

    public static String serialize(Object object, JsonSerializerOptions options) {
        Queue<JsonToken> tokens = new LinkedList<>();

        if (!options.hasConverter(object.getClass())) {
            throw new JsonException("'%s' can not deserialize".formatted(object.getClass().getName()));
        }

        JsonConverter converter = options.getConverter(object.getClass());
        converter.serialize(tokens, object, options);

        // FIXME: Unreachable
        if (tokens.isEmpty()) {
            throw new UnsupportedOperationException("'%s' serialization is not supported".formatted(object.getClass()));
        }

        return JsonFormatter.formatTokens(tokens, options);
    }
}

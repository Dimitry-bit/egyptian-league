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

import java.lang.reflect.Type;
import java.util.Queue;

public abstract class JsonConverter<T> {

    public abstract Type getMyType();

    public boolean canConvert(Type typeToConvert) {
        if (typeToConvert == null) {
            return false;
        }

        if (typeToConvert instanceof Class<?>) {
            return ((Class<?>) getMyType()).isAssignableFrom((Class<?>) typeToConvert);
        }

        return false;
    }

    public abstract void serialize(Queue<JsonToken> tokens, T value, JsonSerializerOptions options);

    public abstract T deserialize(JsonElement element, Type typeToConvert, JsonSerializerOptions options);
}

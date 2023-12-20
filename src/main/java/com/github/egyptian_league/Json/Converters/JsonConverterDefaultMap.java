package com.github.egyptian_league.Json.Converters;

import java.util.Map;

import com.github.egyptian_league.Json.TypeToken;

public class JsonConverterDefaultMap extends JsonConverterMap<Object, Object> {

    @Override
    public TypeToken<Map<Object, Object>> getMyType() {
        return new TypeToken<>() {
        };
    }
}

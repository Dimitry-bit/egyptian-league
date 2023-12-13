package com.github.egyptian_league.json.Converters;

import java.util.Map;

import com.github.egyptian_league.json.TypeToken;

public class JsonConverterDefaultMap extends JsonConverterMap<Object, Object> {

    @Override
    public TypeToken<Map<Object, Object>> getMyType() {
        return new TypeToken<Map<Object, Object>>() {
        };
    }
}

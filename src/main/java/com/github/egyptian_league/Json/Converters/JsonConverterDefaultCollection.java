package com.github.egyptian_league.Json.Converters;

import java.util.Collection;

import com.github.egyptian_league.Json.TypeToken;

public class JsonConverterDefaultCollection extends JsonConverterCollection<Object> {

    @Override
    public TypeToken<Collection<Object>> getMyType() {
        return new TypeToken<>() {
        };
    }
}

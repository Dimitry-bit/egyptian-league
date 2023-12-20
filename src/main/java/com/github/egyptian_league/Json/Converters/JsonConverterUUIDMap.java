package com.github.egyptian_league.Json.Converters;

import java.util.Map;
import java.util.UUID;

import com.github.egyptian_league.Json.TypeToken;

public class JsonConverterUUIDMap extends JsonConverterMap<UUID, Object> {

    @Override
    public TypeToken<Map<UUID, Object>> getMyType() {
        return new TypeToken<>() {
        };
    }
}

package com.github.egyptian_league.json.Converters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;

import com.github.egyptian_league.json.JsonConverter;
import com.github.egyptian_league.json.JsonElement;
import com.github.egyptian_league.json.JsonException;
import com.github.egyptian_league.json.JsonObject;
import com.github.egyptian_league.json.JsonSerializerOptions;

public class JsonConverterUUIDMap extends JsonConverterMap {

    @Override
    public boolean canConvert(Type typeToConvert) {
        if (typeToConvert == null) {
            return false;
        }

        if (typeToConvert instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) typeToConvert;

            if (!(parameterizedType.getRawType() instanceof Class<?>)) {
                return false;
            }

            Class<?> rawTypeClass = (Class<?>) parameterizedType.getRawType();
            if (!Map.class.isAssignableFrom(rawTypeClass)) {
                return false;
            }

            Type arg0 = parameterizedType.getActualTypeArguments()[0];
            if (!(arg0 instanceof Class<?>)) {
                return false;
            }

            return ((Class<?>) arg0).isAssignableFrom(UUID.class);
        }

        return false;
    }

    @Override
    public Map<UUID, Object> deserialize(JsonElement element, Type typeToConvert, JsonSerializerOptions options) {
        if (!canConvert(typeToConvert)) {
            throw new IllegalArgumentException("'%s' type is not a collection".formatted(typeToConvert.getTypeName()));
        }

        if (element.isJsonNull()) {
            return null;
        }

        if (!element.isJsonObject()) {
            throw new IllegalArgumentException(
                    "'%s' JsonElement is not a JsonObject".formatted(element.getClass().getName()));
        }

        ParameterizedType parameterizedType = (ParameterizedType) typeToConvert;
        Class<?> mapType = (Class<?>) parameterizedType.getRawType();
        JsonObject j = element.getAsJsonObject();
        Type keyType = parameterizedType.getActualTypeArguments()[0];
        Type valueType = parameterizedType.getActualTypeArguments()[1];

        if (!(keyType instanceof Class) || !((Class<?>) keyType).isAssignableFrom(UUID.class)) {
            throw new JsonException("'%s' key is supported".formatted(keyType.getTypeName()));
        }

        try {
            Map<UUID, Object> map = (Map<UUID, Object>) mapType.getConstructor().newInstance();

            for (String key : j.keySet()) {
                JsonElement valueElement = j.get(key);
                Object v = null;

                if (!options.hasConverter(valueType)) {
                    throw new JsonException("'%s' can not deserialize".formatted(valueType.getTypeName()));
                }

                if (valueType instanceof ParameterizedType) {
                    if (!options.hasConverter(((ParameterizedType) valueType).getActualTypeArguments()[0])) {
                        throw new JsonException("'%s' can not deserialize".formatted(valueType.getTypeName()));
                    }

                    JsonConverter converter = options.getConverter(valueType);
                    v = converter.deserialize(valueElement, ((ParameterizedType) valueType), options);
                } else if (valueType instanceof Class) {
                    JsonConverter converter = options.getConverter(valueType);
                    v = converter.deserialize(valueElement, valueType, options);
                }

                map.put(UUID.fromString(key), v);
            }

            return map;
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

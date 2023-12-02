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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Supported Types:
 * 
 * Type Name                    | S | D
 * _____________________________|___|___
 * Primitives                   | T | T 
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
 * Nested Collections
 * Maps
 * Date
 * Byte
 */

public class JsonSerializer {

    private final static int MAX_DEPTH = 65;
    private final static Hashtable<Class<?>, Class<?>> wrapperToPrimitiveTable = new Hashtable<>();
    static {
        wrapperToPrimitiveTable.put(Short.class, Short.TYPE);
        wrapperToPrimitiveTable.put(Integer.class, Integer.TYPE);
        wrapperToPrimitiveTable.put(Long.class, Long.TYPE);
        wrapperToPrimitiveTable.put(Float.class, Float.TYPE);
        wrapperToPrimitiveTable.put(Double.class, Double.TYPE);
        wrapperToPrimitiveTable.put(Character.class, Character.TYPE);
        wrapperToPrimitiveTable.put(Boolean.class, Boolean.TYPE);
        wrapperToPrimitiveTable.put(Byte.class, Byte.TYPE);
    }

    private JsonSerializer() {
    }

    public static <T> T deserialize(String source, Class<T> type) {
        JsonObject jsonObject = new JsonObject(source);
        T out = type.cast(deserializeObject(jsonObject, type, 0));

        return out;
    }

    public static String serialize(Object object, JsonSerializerOptions options) {
        Queue<JsonToken> tokens = null;

        if (object.getClass().isArray()) {
            tokens = serializeArray(object);
        } else if (Collection.class.isAssignableFrom(object.getClass())) {
            tokens = serializeCollection(object);
        } else if (!object.getClass().isMemberClass()) {
            tokens = serializeObject(object);
        }

        // FIXME: Unreachable
        if (tokens == null) {
            throw new UnsupportedOperationException("'%s' serialization is not supported".formatted(object.getClass()));
        }

        return tokensToString(tokens, options);
    }

    public static Object deserializeElement(JsonElement element, Class<?> type, int depth) {
        if (depth >= MAX_DEPTH) {
            throw new JsonException("Exceeded max depth");
        }

        if (type.isInterface() && Modifier.isAbstract(type.getModifiers())) {
            throw new IllegalArgumentException(
                    "'%s': Abstract classes & Interfaced are not supported".formatted(type.getName()));
        }

        try {
            if (element.isJsonNull()) {
                return null;
            }

            if (type.isEnum()) {
                return deserializeEnum(element, type);
            }

            if (isPrimitiveOrWrapper(type)) {
                return deserializePrimitive(element, type);
            }

            if (type.isArray()) {
                return deserializeArray(element, type, depth + 1);
            }

            if (type.isAssignableFrom(String.class)) {
                return deserializeString(element, type);
            }

            boolean isOuterClass = !type.isMemberClass() && !type.isAnonymousClass()
                    && !type.isLocalClass() && !type.isInterface();

            if (isOuterClass && element.isJsonObject()) {
                return deserializeObject(element, type, depth + 1);
            }
        } catch (IllegalAccessException e) {
            System.err.println("deserialization: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private static Object deserializePrimitive(JsonElement element, Class<?> type) throws IllegalAccessException {
        if (!isPrimitiveOrWrapper(type)) {
            throw new IllegalArgumentException("'%s' is neither a primitive nor a wrapper".formatted(type.getName()));
        }

        if (!element.isJsonPrimitive()) {
            throw new IllegalArgumentException("JsonElement is not a JsonPrimitive");
        }

        JsonPrimitive jsonPrimitive = element.getAsJsonPrimitive();
        type = wrapperToPrimitive(type);

        if (type == Byte.TYPE) {
            return null;
        }

        if (Number.class.isInstance(jsonPrimitive.value)) {
            Number n = jsonPrimitive.getAsNumber();

            if (type == Short.TYPE) {
                return n.shortValue();
            }
            if (type == Integer.TYPE) {
                return n.intValue();
            }
            if (type == Long.TYPE) {
                return n.longValue();
            }
            if (type == Float.TYPE) {
                return n.floatValue();
            }
            if (type == Double.TYPE) {
                return n.doubleValue();
            }
        }

        if (type == Character.TYPE) {
            return jsonPrimitive.getAsCharacter();
        }

        if (type == Boolean.TYPE) {
            return jsonPrimitive.getAsBoolean();

        }

        return null;
    }

    private static Object deserializeString(JsonElement element, Class<?> type) {
        if (!type.isAssignableFrom(String.class)) {
            throw new IllegalArgumentException("'%s' is not assignable from string".formatted(type.getName()));
        }

        return element.getAsJsonPrimitive().getAsString();
    }

    private static Object deserializeEnum(JsonElement element, Class<?> type) {
        if (!type.isEnum()) {
            throw new IllegalArgumentException("'%s' is not an enum".formatted(type.getName()));
        }

        if (!element.isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    "'%s' JsonElement is not a JsonPrimitive".formatted(element.getClass().getName()));
        }

        int index = element.getAsJsonPrimitive().getAsNumber().intValue();
        return type.getEnumConstants()[index];
    }

    private static <T> T deserializeObject(JsonElement element, Class<T> objectType, int depth) {
        boolean isOuterClass = !objectType.isMemberClass() && !objectType.isAnonymousClass()
                && !objectType.isLocalClass() && !objectType.isInterface();

        if (!isOuterClass) {
            throw new UnsupportedOperationException("'%s' is not an outer class".formatted(objectType.getName()));
        }

        if (!element.isJsonObject()) {
            throw new IllegalArgumentException(
                    "'%s' JsonElement is not a JsonObject".formatted(element.getClass().getName()));
        }

        try {
            objectType.getConstructor().setAccessible(true);
            T o = objectType.getConstructor().newInstance();

            JsonObject jsonObject = element.getAsJsonObject();
            ArrayList<Field> fields = new ArrayList<>();

            Collections.addAll(fields, objectType.getFields());
            Collections.addAll(fields, objectType.getDeclaredFields());

            for (Field field : fields) {
                String key = field.getName();
                Object value = null;
                JsonElement valueElement = null;

                if (!jsonObject.containsKey(key)) {
                    continue;
                }

                valueElement = jsonObject.get(key);

                if (!element.isJsonNull()) {

                    boolean isValidValue = ((value = deserializeElement(valueElement, field.getType(), depth)) != null)
                            || ((value = deserializeCollection(valueElement, field, depth + 1)) != null);

                    if (!isValidValue) {
                        throw new UnsupportedOperationException("'%s': field deserialization is not supported"
                                .formatted(field.getType().getSimpleName()));
                    }
                }

                field.setAccessible(true);
                field.set(o, value);
            }

            return o;
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

    private static Object deserializeArray(JsonElement element, Class<?> type, int depth)
            throws IllegalAccessException {
        if (!type.isArray()) {
            throw new IllegalAccessException("'%s' type is not an array".formatted(type.getName()));
        }

        if (!element.isJsonArray()) {
            throw new IllegalAccessException(
                    "'%s' JsonElement is not a JsonArray".formatted(element.getClass().getName()));
        }

        JsonArray jsonArray = element.getAsJsonArray();
        Object array = Array.newInstance(type.getComponentType(), jsonArray.size());
        int length = Array.getLength(array);

        for (int i = 0; i < length; ++i) {
            JsonElement t = jsonArray.get(i);
            Object v = deserializeElement(t, type.getComponentType(), depth);
            Array.set(array, i, v);
        }

        return array;
    }

    private static Object deserializeCollection(JsonElement element, Field field, int depth)
            throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Class<?> collectionType = field.getType();

        if (!Collection.class.isAssignableFrom(collectionType)) {
            throw new IllegalAccessException("'%s' type is not a collection".formatted(collectionType.getName()));
        }

        if (collectionType.isInterface() && Modifier.isAbstract(collectionType.getModifiers())) {
            throw new IllegalArgumentException(
                    "'%s': Abstract classes & Interfaced are not supported".formatted(collectionType.getName()));
        }

        if (!element.isJsonArray()) {
            throw new IllegalAccessException("JsonElement is not a JsonArray");
        }

        JsonArray j = element.getAsJsonArray();
        Collection<Object> collection = (Collection<Object>) collectionType.getConstructor().newInstance();

        ParameterizedType pTypes = ((ParameterizedType) field.getGenericType());
        if ((pTypes.getActualTypeArguments()[0] instanceof ParameterizedType)) {
            throw new UnsupportedOperationException("Nested Collections are not supported");
        }

        if (!(pTypes.getActualTypeArguments()[0] instanceof Class)) {
            throw new JsonException("Could not extract generic type");
        }

        Class<?> componentType = (Class<?>) pTypes.getActualTypeArguments()[0];
        for (int i = 0; i < j.size(); ++i) {
            JsonElement e = j.get(i);
            Object v = deserializeElement(e, componentType, depth);
            collection.add(v);
        }

        return collection;
    }

    private static Queue<JsonToken> serializeValue(Object object) {
        Queue<JsonToken> tokens = new LinkedList<>();

        if (object == null) {
            tokens.add(new JsonToken("null", JsonTokenType.NULL));
            return tokens;
        }

        Class<?> valueType = object.getClass();

        if (isPrimitiveOrWrapper(valueType)) {
            tokens.add(serializePrimitive(object));
        } else if (valueType.isArray()) {
            return serializeArray(object);
        } else if (valueType.isEnum()) {
            tokens.add(serializeEnum(object));
        } else if (valueType.isAssignableFrom(String.class)) {
            tokens.add(serializeString(object));
        } else if (!valueType.isMemberClass()) {
            return serializeObject(object);
        }

        return tokens;
    }

    private static JsonToken serializePrimitive(Object object) {
        Class<?> type = object.getClass();
        Class<?> primitiveType = wrapperToPrimitive(type);

        if (primitiveType == Byte.TYPE) {
            throw new UnsupportedOperationException("'%s' is unsupported".formatted(primitiveType.getSimpleName()));
        }

        if (primitiveType == Character.TYPE) {
            return new JsonToken("\"" + ((Character) object) + "\"", JsonTokenType.STRING);
        }

        if (primitiveType == Boolean.TYPE) {
            return new JsonToken(object.toString(), JsonTokenType.BOOLEAN);
        }

        if (Number.class.isAssignableFrom(type)) {
            return new JsonToken(object.toString(), JsonTokenType.NUMBER);
        }

        throw new IllegalArgumentException("'%s' is not a primitive type".formatted(type.getName()));
    }

    private static JsonToken serializeString(Object object) {
        return new JsonToken("\"" + ((String) object) + "\"", JsonTokenType.STRING);
    }

    private static JsonToken serializeEnum(Object object) {
        return new JsonToken(((Integer) (((Enum<?>) object).ordinal())).toString(), JsonTokenType.NUMBER);
    }

    private static Queue<JsonToken> serializeObject(Object object) {
        Queue<JsonToken> tokens = new LinkedList<>();
        ArrayList<Field> fields = new ArrayList<>();
        Class<?> objectClass = object.getClass();

        Collections.addAll(fields, objectClass.getFields());
        Collections.addAll(fields, objectClass.getDeclaredFields());

        try {
            tokens.add(new JsonToken("{", JsonTokenType.OBJECT_START));
            for (int i = 0; i < fields.size(); ++i) {
                if (i != 0) {
                    tokens.add(new JsonToken(",", JsonTokenType.COMMA));
                }

                Field f = fields.get(i);
                Class<?> valueType = f.getType();

                f.setAccessible(true);
                Object value = f.get(object);

                tokens.add(new JsonToken('"' + f.getName() + '"', JsonTokenType.STRING));
                tokens.add(new JsonToken(":", JsonTokenType.COLON));

                if (Collection.class.isAssignableFrom(valueType)) {
                    tokens.addAll(serializeCollection(value));
                } else {
                    tokens.addAll(serializeValue(value));
                }
            }
            tokens.add(new JsonToken("}", JsonTokenType.OBJECT_END));

            return tokens;
        } catch (IllegalAccessException e) {
            System.err.println("serialization: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private static Queue<JsonToken> serializeArray(Object object) {
        Queue<JsonToken> tokens = new LinkedList<>();
        int length = Array.getLength(object);

        tokens.add(new JsonToken("[", JsonTokenType.ARRAY_START));
        for (int i = 0; i < length; ++i) {
            if (i != 0) {
                tokens.add(new JsonToken(",", JsonTokenType.COMMA));
            }

            Object value = Array.get(object, i);
            tokens.addAll(serializeValue(value));
        }
        tokens.add(new JsonToken("]", JsonTokenType.ARRAY_END));

        return tokens;
    }

    private static Queue<JsonToken> serializeCollection(Object object) {
        Queue<JsonToken> tokens = new LinkedList<>();
        Collection<?> collection = (Collection<?>) object;
        boolean printComma = false;

        tokens.add(new JsonToken("[", JsonTokenType.ARRAY_START));
        for (Object value : collection) {
            if (printComma) {
                tokens.add(new JsonToken(",", JsonTokenType.COMMA));
            }

            tokens.addAll(serializeValue(value));
            printComma = true;
        }
        tokens.add(new JsonToken("]", JsonTokenType.ARRAY_END));

        return tokens;
    }

    private static boolean isPrimitiveOrWrapper(Class<?> type) {
        return (type.isPrimitive() && type != void.class) ||
                type == Double.class || type == Float.class || type == Long.class ||
                type == Integer.class || type == Short.class || type == Character.class ||
                type == Byte.class || type == Boolean.class;
    }

    private static Class<?> wrapperToPrimitive(Class<?> wrapperType) {
        if (wrapperType.isPrimitive()) {
            return wrapperType;
        }

        return wrapperToPrimitiveTable.get(wrapperType);
    }

    private static String tokensToString(Queue<JsonToken> tokens, JsonSerializerOptions options) {
        StringBuilder sb = new StringBuilder();

        if (!options.WriteIndented) {
            while (!tokens.isEmpty()) {
                sb.append((tokens.poll()).value);
            }

            return sb.toString();
        }

        // WriteIndented
        {
            int nTabs = 0;
            int tabWidth = options.tabWidth;
            boolean indent = false;

            while (!tokens.isEmpty()) {
                JsonToken token = tokens.poll();

                boolean isTokenStart = (token.type == JsonTokenType.ARRAY_START)
                        || (token.type == JsonTokenType.OBJECT_START);

                boolean isNextTokenEnd = !tokens.isEmpty() && ((tokens.peek().type == JsonTokenType.ARRAY_END)
                        || (tokens.peek().type == JsonTokenType.OBJECT_END));

                boolean isTokenStartOrComma = isTokenStart || (token.type == JsonTokenType.COMMA);

                if (indent) {
                    sb.append(" ".repeat(tabWidth).repeat(nTabs));
                    indent = false;
                }

                sb.append(token.value);

                if (isTokenStartOrComma || isNextTokenEnd) {
                    sb.append("\n");
                    indent = true;

                    if (isTokenStart) {
                        nTabs++;
                    } else if (isNextTokenEnd) {
                        nTabs--;
                    }
                } else if (token.type == JsonTokenType.COLON) {
                    sb.append(" ");
                }
            }
        }

        return sb.toString();
    }
}
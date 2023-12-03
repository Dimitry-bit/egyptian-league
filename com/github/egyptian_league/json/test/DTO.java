package com.github.egyptian_league.json.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

import com.github.egyptian_league.json.src.JsonTokenType;

public class DTO {
    public String string;
    public short shortNumber;
    public int intNumber;
    public long longNumber;
    public float floatNumber;
    public double doubleNumber;
    public char character;
    public boolean bool;
    public Byte byteVar;
    public UUID uuid;
    public LocalDateTime date;
    public JsonTokenType tokenType;
    public WrappersDTO child;
    public WrappersDTO nullChild;
    public int[] ints;
    public ArrayList<Integer> collection;
    public Hashtable<String, Integer> hashtable;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("string = %s\n".formatted(string));
        sb.append("shortNumber = %d\n".formatted(shortNumber));
        sb.append("intNumber = %d\n".formatted(intNumber));
        sb.append("longNumber = %d\n".formatted(longNumber));
        sb.append("floatNumber = %f\n".formatted(floatNumber));
        sb.append("doubleNumber = %f\n".formatted(doubleNumber));
        sb.append("character = %c\n".formatted(character));
        sb.append("bool = %s\n".formatted(bool));
        sb.append("byteVar = %d\n".formatted(byteVar));
        sb.append("UUID = %s\n".formatted(uuid));
        sb.append("Date = %s\n".formatted(date));
        sb.append("tokenType = %s\n".formatted(tokenType));
        sb.append("nullChild = %s\n".formatted(nullChild));

        sb.append("ints = [ ");
        for (int i = 0; i < ints.length; ++i) {
            if (i != 0) {
                sb.append(", ");
            }

            sb.append(ints[i]);
        }
        sb.append(" ]\n");

        sb.append("collection = [ ");
        for (int i = 0; i < collection.size(); ++i) {
            if (i != 0) {
                sb.append(", ");
            }

            sb.append(collection.get(i));
        }
        sb.append(" ]\n");

        sb.append("Child: {\n");
        sb.append(child.toString().indent(4));
        sb.append("}\n");

        return sb.toString();
    }
}

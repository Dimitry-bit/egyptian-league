package com.github.egyptian_league.json.test;

import java.util.ArrayList;

import com.github.egyptian_league.json.src.JsonTokenType;

public class WrappersDTO {
    private String string;
    private Short shortNumber;
    private Integer intNumber;
    private Long longNumber;
    private Float floatNumber;
    private Double doubleNumber;
    private Character character;
    private Boolean bool;
    private JsonTokenType tokenType;
    private WrappersDTO nullChild;
    private Integer[] ints;
    private ArrayList<Integer> collection;

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

        return sb.toString();
    }
}
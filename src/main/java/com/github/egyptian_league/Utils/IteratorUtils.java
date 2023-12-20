package com.github.egyptian_league.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorUtils {
    private IteratorUtils() {
    }

    public static int size(Iterator<?> iterator) {
        int size = 0;

        while (iterator.hasNext()) {
            size++;
        }

        return size;
    }

    public static <T> List<T> toList(Iterator<T> iterator) {
        List<T> list = new ArrayList<>();

        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        return list;
    }
}

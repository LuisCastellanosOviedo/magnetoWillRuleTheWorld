package com.meli.xmen.util;

import java.util.List;
import java.util.Objects;

public class ArrayUtils {

    private ArrayUtils() {
    }

    public static String[] listStringToArray(List<String> strings) {
        if (Objects.isNull(strings)){
            return new String[0];
        }
        String[] array = new String[strings.size()];
        array = strings.toArray(array);
        return array;
    }
}

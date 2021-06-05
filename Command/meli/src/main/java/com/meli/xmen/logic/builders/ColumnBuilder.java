package com.meli.xmen.logic.builders;

public class ColumnBuilder {

    public static String buildColumn(String[] dna, int index) {
        StringBuilder column = new StringBuilder();
        for (int i = 0; i < dna.length; i++) {
            column.append(dna[i].charAt(index));
        }
        return column.toString();
    }
}

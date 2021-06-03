package com.meli.xmen.core;

public class XmenDNAFinder {

    public static int countMutantDNAPresent(String row, int rowIndex, int acc) {

        int accSet = 0;

        if (acc == 4) {
            accSet = accSet + 1;
            acc = 0;
        }
        if (rowIndex == row.length() - 1) {
            return accSet;
        } else {
            if (row.charAt(rowIndex) == row.charAt(rowIndex + 1)) {
                acc = acc + 1;
            } else {
                acc = 1;
            }
            return accSet + countMutantDNAPresent(row, rowIndex + 1, acc);
        }

    }
}

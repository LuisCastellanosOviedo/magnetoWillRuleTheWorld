package com.meli.xmen.logic;

import static com.meli.xmen.constants.XmenConstants.DNA_VALID_SIZE_BLOCK_TO_BE_MUTANT;

public class XmenDNAFinderAlgorithm {


    public static int countMutantDNAPresentOnSingleChain(String letters) {
        return XmenDNAFinderAlgorithm.countMutantDNAPresent(letters, 0, 1);
    }

    private static int countMutantDNAPresent(String row, int rowIndex, int acc) {

        int accSet = 0;

        if (acc == DNA_VALID_SIZE_BLOCK_TO_BE_MUTANT) {
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

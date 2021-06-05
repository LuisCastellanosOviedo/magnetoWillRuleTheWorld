package com.meli.xmen.logic.builders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.meli.xmen.constants.XmenConstants.DNA_VALID_SIZE_BLOCK_TO_BE_MUTANT;

public class DiagonalBuilder {

    public static List<String> buildDiagonals(String[] dna) {

        List<String> diag = IntStream.rangeClosed(1, dna.length - DNA_VALID_SIZE_BLOCK_TO_BE_MUTANT)
                .mapToObj(i -> buildUpperDiagonal(dna, i))
                .collect(Collectors.toList());

        diag.addAll(IntStream.rangeClosed(1, dna.length - DNA_VALID_SIZE_BLOCK_TO_BE_MUTANT)
                .mapToObj(i -> buildlowerDiagonal(dna, i))
                .collect(Collectors.toList()));

        diag.add(buildMainDiagonal(dna));

        return diag;
    }

    private static String buildlowerDiagonal(String[] dna, int startAt) {
        StringBuilder diagonal = new StringBuilder();
        int diagonales = dna.length - startAt;
        int start = startAt;
        for (int i = 0; i < diagonales; i++) {
            diagonal.append(dna[start].charAt(i));
            start++;
        }
        return diagonal.toString();
    }

    private static String buildUpperDiagonal(String[] dna, int startAt) {
        StringBuilder diagonal = new StringBuilder();
        int diagonales = dna.length - startAt;
        for (int i = 0; i < diagonales; i++) {
            diagonal.append(dna[i].charAt(startAt));
            startAt++;
        }
        return diagonal.toString();
    }

    private static String buildMainDiagonal(String[] dna) {
        StringBuilder diagonal = new StringBuilder();
        for (int i = 0; i < dna.length; i++) {
            diagonal.append(dna[i].charAt(i));
        }
        return diagonal.toString();
    }

}

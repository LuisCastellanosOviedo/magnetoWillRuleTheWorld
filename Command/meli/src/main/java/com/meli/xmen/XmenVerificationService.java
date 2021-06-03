package com.meli.xmen;

import com.meli.dna.DNAVerifier;
import com.meli.xmen.core.XmenDNAFinder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class XmenVerificationService implements DNAVerifier {

    private static Predicate<String[]> isValidData = x -> Objects.isNull(x) || x.length < 4;

    private static Predicate<Integer> isMutant = x -> x > 1;

    @Override
    public boolean execute(String[] dna) throws ExecutionException, InterruptedException {


        if (IsNotValidData(dna, isValidData)) {
            return false;
        }

        int diagonal = countMutantDNAPresentOnSingleChain(buildDiagonal(dna));

        if (isMutant(diagonal,isMutant)) {
            return true;
        }

        int dnaOnRows = countMutantDNAPresentOnRows(dna);

        if (isMutant(diagonal + dnaOnRows, isMutant)) {
            return true;
        }

        int dnaOnColumns = countMutantDNAPresentOnColumns(dna);

        return isMutant(diagonal + dnaOnRows + dnaOnColumns, isMutant);

    }

    private boolean IsNotValidData(String[] dna, Predicate isDataValid) {
        return isDataValid.test(dna);
    }

    private int countMutantDNAPresentOnSingleChain(String letters){
        return XmenDNAFinder.countMutantDNAPresent(letters,0,1);
    }

    private boolean isMutant(int diagonal, Predicate<Integer> isMutant) {
        return isMutant.test(diagonal);
    }

    private Integer countMutantDNAPresentOnDiagonal(String[] dna) throws ExecutionException, InterruptedException {
        ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        int counter = 0;
        try{
            counter = customThreadPool.submit(() ->
                    IntStream.rangeClosed(0, ((dna.length - 4)*2) )
                            .parallel()
                            .map(i -> countMutantDNAPresentOnSingleChain(buildCol(dna, i)))
                            .sum())
                    .get();
        }finally {
            customThreadPool.shutdown();
        }
        return counter;
    }

    private Integer countMutantDNAPresentOnRows(String[] dna) throws ExecutionException, InterruptedException {
        ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        int counter = 0;
        try {
            customThreadPool.submit(() -> Stream.of(dna)
                    .parallel()
                    .mapToInt(x -> countMutantDNAPresentOnSingleChain(x))
                    .sum())
                    .get();
        }finally {
            customThreadPool.shutdown();
        }
        return counter;
    }

    private Integer countMutantDNAPresentOnColumns(String[] dna) throws InterruptedException, ExecutionException {
        ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        int counter = 0;
        try{
            counter = customThreadPool.submit(() ->
                    IntStream.rangeClosed(0, dna.length - 1)
                            .parallel()
                            .map(i -> countMutantDNAPresentOnSingleChain(buildCol(dna, i)))
                            .sum())
                            .get();
        }finally {
            customThreadPool.shutdown();
        }
        return counter;
    }

    private String buildCol(String[] dna, int index) {
        StringBuilder diagonal = new StringBuilder();
        for (int i = 0; i < dna.length; i++) {
            diagonal.append(dna[i].charAt(index));
        }
        return diagonal.toString();
    }

    private String buildDiagonal(String[] dna) {
        StringBuilder diagonal = new StringBuilder();
        for (int i = 0; i < dna.length; i++) {
            diagonal.append(dna[i].charAt(i));
        }
        return diagonal.toString();
    }

    private String buildDiagonalInversed(String[] dna) {

        List<String> listOfProducts = Arrays.asList(dna);
        Collections.reverse(listOfProducts);

        return buildDiagonal((String[]) listOfProducts.toArray());
    }


}

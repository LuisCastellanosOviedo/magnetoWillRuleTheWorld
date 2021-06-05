package com.meli.xmen.logic.dnaMatchers;

import com.meli.xmen.model.dto.MutantDto;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.function.Predicate;

public class DnaCounters {

    public static MutantDto findMutantDna(MutantDto mutantDto, Predicate<Integer> isMutant,
                                          Function<MutantDto, Integer> possibleMutantStructure)
            throws ExecutionException, InterruptedException {

        ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        int counter = 0;
        try {
            if (!isMutant.test(mutantDto.getDnaMutantCount())) {
                counter = customThreadPool.submit(() ->
                        possibleMutantStructure.apply(mutantDto)
                ).get();
            }
        } finally {
            customThreadPool.shutdown();
        }
        return MutantDto.builder().dnaMutantCount(counter + mutantDto.getDnaMutantCount()).build();
    }

}

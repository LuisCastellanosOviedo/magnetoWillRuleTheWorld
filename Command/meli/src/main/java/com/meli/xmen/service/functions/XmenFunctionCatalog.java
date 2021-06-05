package com.meli.xmen.service.functions;

import com.meli.xmen.logic.builders.ColumnBuilder;
import com.meli.xmen.model.dto.MutantDto;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.meli.xmen.constants.XmenConstants.DNA_VALID_SIZE_BLOCK_TO_BE_MUTANT;
import static com.meli.xmen.logic.XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain;

public class XmenFunctionCatalog {

    public static Predicate<String[]> isValidData = x -> Objects.isNull(x) ||
            x.length < DNA_VALID_SIZE_BLOCK_TO_BE_MUTANT;

    public static Predicate<Integer> isMutant = x -> x > 1;

    public static Function<MutantDto, Integer> diagonal = x ->
            Stream.of(x.getDna())
                    .parallel()
                    .mapToInt(i -> countMutantDNAPresentOnSingleChain(i))
                    .sum();

    public static Function<MutantDto, Integer> row = x ->
            Stream.of(x.getDna())
                    .parallel()
                    .mapToInt(y -> countMutantDNAPresentOnSingleChain(y))
                    .sum();

    public static Function<MutantDto, Integer> column = x -> IntStream.rangeClosed(0, x.getDna().length - 1)
            .parallel()
            .map(i -> countMutantDNAPresentOnSingleChain(ColumnBuilder.buildColumn(x.getDna(), i)))
            .sum();
}

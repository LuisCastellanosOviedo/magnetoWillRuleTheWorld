package com.meli.xmen.service;

import com.meli.xmen.dna.DNAVerifier;
import com.meli.xmen.logic.builders.DiagonalBuilder;
import com.meli.xmen.model.dto.MutantDto;
import com.meli.xmen.persistence.repository.DynamoRepository;
import com.meli.xmen.persistence.repository.model.DnaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.meli.xmen.logic.dnaMatchers.DnaCounters.findMutantDna;
import static com.meli.xmen.service.functions.XmenFunctionCatalog.*;
import static com.meli.xmen.util.ArrayUtils.listStringToArray;

@Service
public class XmenVerificationService implements DNAVerifier {

    @Autowired
    private DynamoRepository dynamoRepository;

    @Override
    public boolean execute(String[] dna) throws ExecutionException, InterruptedException {

        if (IsNotValidData(dna, isValidData)) {
            return false;
        }

        Boolean mutant = processDna(dna);
        persistDna(dna, mutant);
        return mutant;
    }

    private Boolean processDna(String[] dna) throws ExecutionException, InterruptedException {
        MutantDto mutantDto = findMutantDna(new MutantDto(0, listStringToArray(DiagonalBuilder.buildDiagonals(dna))), isMutant, diagonal);
        mutantDto = findMutantDna(new MutantDto(mutantDto.getDnaMutantCount(), dna), isMutant, row);
        mutantDto = findMutantDna(new MutantDto(mutantDto.getDnaMutantCount(), dna), isMutant, column);

        return isMutant.test(mutantDto.getDnaMutantCount());
    }

    private void persistDna(String[] dna, Boolean mutant) {
        dynamoRepository.save(DnaEntity.builder()
                .dnaChain(Stream.of(dna)
                        .collect(Collectors.joining(" ")))
                .isMutant(mutant.toString())
                .build());
    }

    private boolean IsNotValidData(String[] dna, Predicate isDataValid) {
        return isDataValid.test(dna);
    }


}

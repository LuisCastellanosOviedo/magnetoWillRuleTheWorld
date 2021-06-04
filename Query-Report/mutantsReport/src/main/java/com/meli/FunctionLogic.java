package com.meli;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.meli.model.MutantReport;
import com.meli.repository.DynamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component(value = "func-logic")
public class FunctionLogic implements Function<Object, ResponseEntity<MutantReport>> {

    @Autowired
    private DynamoRepository dynamoRepository;


    @Override
    public  ResponseEntity<MutantReport> apply(Object o) {

        Integer mutants = findMutants2(Boolean.TRUE.toString().toLowerCase());
        Integer humans = findMutants2(Boolean.FALSE.toString().toLowerCase());

        MutantReport mutantReport = MutantReport.builder()
                .count_human_dna(humans.intValue())
                .count_mutant_dna(mutants.intValue())
                .ratio(getRatio(mutants, humans))
                .build();

        return new ResponseEntity(mutantReport, HttpStatus.OK);
    }

    private float getRatio(Integer mutants, Integer humans) {
        return humans>0 ? mutants.floatValue()/humans.floatValue():0;
    }

    public Integer findMutants2(
            String isMutant) {
        String tableName = "dna_mutant_store";
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put(":isMutant", new AttributeValue().withS(isMutant));

        String query =
                "isMutant = :isMutant";
        return dynamoRepository.findByAttribute3(tableName, attributes, query);
    }


}

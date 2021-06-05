package com.meli.xmen.repository.dynamodb;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.meli.xmen.repository.dataaccess.XmenDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Primary
public class NoSQLDataAccess implements XmenDataAccess {

    private static final String DNA_MUTANT_TABLE_NAME = "dna_mutant_store";
    private final DynamoRepository dynamoRepository;

    @Autowired
    public NoSQLDataAccess(DynamoRepository dynamoRepository) {
        this.dynamoRepository = dynamoRepository;
    }

    public Integer findPeopleByType(
            String isMutant) {
        String tableName = DNA_MUTANT_TABLE_NAME;
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put(":isMutant", new AttributeValue().withS(isMutant.toLowerCase()));

        String query =
                "isMutant = :isMutant";
        return dynamoRepository.findPeopleByType(tableName, attributes, query);
    }
}

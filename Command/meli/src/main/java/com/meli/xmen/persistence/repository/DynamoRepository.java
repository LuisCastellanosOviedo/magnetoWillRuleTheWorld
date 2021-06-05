package com.meli.xmen.persistence.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.meli.xmen.persistence.repository.model.DnaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DynamoRepository {

    private static final String DNA_MUTANT_STORE_DYNAMODB_TABLE_NAME = "dna_mutant_store";
    private final AmazonDynamoDB amazonDynamoDB;

    @Autowired
    public DynamoRepository(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;

    }

    public void save(DnaEntity dnaEntity) {
        getMapper(DNA_MUTANT_STORE_DYNAMODB_TABLE_NAME)
                .save(dnaEntity);
    }

    public DynamoDBMapper getMapper(String tableName) {
        DynamoDBMapperConfig configs =
                new DynamoDBMapperConfig.Builder()
                        .withTableNameOverride(DynamoDBMapperConfig
                                .TableNameOverride.withTableNameReplacement(tableName))
                        .build();
        return new DynamoDBMapper(amazonDynamoDB, configs);
    }


}

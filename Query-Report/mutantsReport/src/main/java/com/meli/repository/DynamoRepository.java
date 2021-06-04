package com.meli.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.Select;
import com.meli.repository.model.DnaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DynamoRepository {

    private final AmazonDynamoDB amazonDynamoDB;


    @Autowired
    public DynamoRepository(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;

    }

    public Integer findByAttribute3(
            String tableName, Map<String, AttributeValue> atributes, String query) {
        ScanRequest queryExpression =
                new ScanRequest()
                        .withTableName(tableName)
                        .withFilterExpression(query)
                        .withExpressionAttributeValues(atributes)
                        .withSelect(Select.COUNT);

        return amazonDynamoDB.scan(queryExpression).getCount();
    }

    public DynamoDBMapper getMapper(String tableName) {
        DynamoDBMapperConfig configs =
                new DynamoDBMapperConfig.Builder()
                        .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(tableName))
                        .build();
        return new DynamoDBMapper(amazonDynamoDB, configs);
    }



}

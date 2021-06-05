package com.meli.xmen.repository.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.Select;
import com.meli.xmen.repository.dataaccess.XmenDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DynamoRepository {

    private final AmazonDynamoDB amazonDynamoDB;

    @Autowired
    public DynamoRepository(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;

    }

    public Integer findPeopleByType(
            String tableName, Map<String, AttributeValue> atributes, String query) {
        ScanRequest queryExpression =
                new ScanRequest()
                        .withTableName(tableName)
                        .withFilterExpression(query)
                        .withExpressionAttributeValues(atributes)
                        .withSelect(Select.COUNT);

        return amazonDynamoDB.scan(queryExpression).getCount();
    }

}

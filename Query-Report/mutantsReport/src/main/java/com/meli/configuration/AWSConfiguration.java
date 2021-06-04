package com.meli.configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfiguration {

    private static final int MAX_NUM_ERROR_RETRY = 1;

    @Bean
    public AmazonDynamoDB dynamoDBCustomClient() {
        ClientConfiguration clientConfiguration =
                new ClientConfiguration().withMaxErrorRetry(MAX_NUM_ERROR_RETRY);
        return AmazonDynamoDBClientBuilder.standard()
                .withClientConfiguration(clientConfiguration)
                .withRegion("us-east-1")
                .build();
    }
    @Bean
    public DynamoDB getDynamoDB() {
        return new DynamoDB(dynamoDBCustomClient());
    }
}

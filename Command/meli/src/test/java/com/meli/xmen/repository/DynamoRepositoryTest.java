package com.meli.xmen.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.meli.xmen.persistence.repository.DynamoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DynamoRepositoryTest {

    @Mock
    private  AmazonDynamoDB amazonDynamoDB;

    @InjectMocks
    private DynamoRepository dynamoRepository;

    @Test
    public void dynamoRepositoryShouldNotNull() {
        Assert.assertNotNull(dynamoRepository);
    }

    @Test
    public void mapperShouldNotNull() {
        Assert.assertNotNull(dynamoRepository.getMapper("test"));
    }
}

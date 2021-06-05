package com.meli.xmen.repository.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class DynamoRepositoryTest {

    @Mock
    private  AmazonDynamoDB amazonDynamoDB;

    @InjectMocks
    private DynamoRepository dynamoRepository;

    @Test
    public void repositoryShoulNotBeNull() {
        Assert.assertNotNull(dynamoRepository);
    }

    @Test
    public void shouldReturnValidResult() {
        ScanResult scanResult = Mockito.mock(ScanResult.class);
        Mockito.when(scanResult.getCount()).thenReturn(3);
        Mockito.when(amazonDynamoDB.scan(Mockito.any())).thenReturn(scanResult);

        Map<String, AttributeValue> atributes = new HashMap<>();

        Integer result = dynamoRepository.findPeopleByType("tablle",atributes,"query");

        Assert.assertEquals(result,new Integer(3));
        Mockito.verify(amazonDynamoDB).scan(Mockito.any());

    }
}

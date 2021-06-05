package com.meli.xmen.repository.dynamodb;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
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
public class NoSQLDataAccessTest {

    @Mock
    private DynamoRepository dynamoRepository;

    @InjectMocks
    private NoSQLDataAccess noSQLDataAccess;

    @Test
    public void NoSqlShouldNotBeNull() {
        Assert.assertNotNull(noSQLDataAccess);
    }

    @Test
    public void shouldReturnValidResponse() {
        Mockito.when(dynamoRepository.findPeopleByType(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(2);

        int result = noSQLDataAccess.findPeopleByType(Boolean.TRUE.toString());
        Assert.assertEquals(result,2);
        Mockito.verify(dynamoRepository).findPeopleByType(Mockito.any(),Mockito.any(),Mockito.any());
    }
}

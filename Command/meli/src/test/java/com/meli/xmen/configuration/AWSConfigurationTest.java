package com.meli.xmen.configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AWSConfigurationTest {

    private AWSConfiguration awsConfiguration;

    @Before
    public void setUp() throws Exception {
        awsConfiguration= new AWSConfiguration();
    }

    @Test
    public void configurationNorNull() {
        Assert.assertNotNull(awsConfiguration);
    }

    @Test
    public void mapperShouldNotBeNull() {
        Assert.assertNotNull(awsConfiguration.dynamoDBCustomClient());
    }

    @Test
    public void dynamoDBShouldNotBeNull() {
        Assert.assertNotNull(awsConfiguration.getDynamoDB());
    }
}

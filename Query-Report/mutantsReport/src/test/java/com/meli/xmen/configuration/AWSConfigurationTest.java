package com.meli.xmen.configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AWSConfigurationTest {

    private AWSConfiguration awsConfiguration;

    @Before
    public void setUp() throws Exception {
        awsConfiguration = new AWSConfiguration();
    }

    @Test
    public void configurationShoulNotbeNull() {
        Assert.assertNotNull(awsConfiguration);
    }

    @Test
    public void dynamoDBClientShoulNotbeNull() {
        Assert.assertNotNull(awsConfiguration.dynamoDBCustomClient());
    }

    @Test
    public void dynamoDBApiShoulNotbeNull() {
        Assert.assertNotNull(awsConfiguration.getDynamoDB());
    }
}

package com.meli.xmen.persistence.repository.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;


@Builder
@AllArgsConstructor
@Getter
@DynamoDBTable(tableName = "")
public class DnaEntity {

    @DynamoDBHashKey(attributeName = "dna_chain")
    private String dnaChain;

    @DynamoDBRangeKey(attributeName = "isMutant")
    private String isMutant;

}

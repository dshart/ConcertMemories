package com.nashss.se.concertmemories.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.GlobalSecondaryIndex;
import com.nashss.se.concertmemories.dynamodb.models.Concert;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.concertmemories.exceptions.ConcertNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import static com.nashss.se.concertmemories.dynamodb.models.Concert.BAND_INDEX;

/**
 * Accesses data for a concert using {@link Concert} to represent the model in DynamoDB.

 */
@Singleton
public class ConcertDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a ConcertDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the Concerts table
     */
    @Inject
    public ConcertDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Retrieves a concert emailAddress and dateAttended.
     * <p>
     * If not found, throws ConcertNotFoundException.
     *
     * @param emailAddress The emailAddress to look up
     * @param dateAttended The dateAttended to look up
     * @return The corresponding Concert if found
     */
    public Concert getConcert(String emailAddress, String dateAttended) {
        Concert concert = dynamoDbMapper.load(Concert.class, emailAddress, dateAttended);

        if (concert == null) {
            throw new ConcertNotFoundException(String.format("Could not find concert with emailAddress %s and dateAttended %s",
                    emailAddress, dateAttended));
        }
        return concert;
    }

    /**
     * Retrieves all concertss matching provided emailAddress.
     * <p>
     * If none found, returns an empty list.
     *
     * @param emailAddress The emailAddress to look up
     * @return A list of Concerts found, if any
     */
    public List<Concert> getAllConcerts(String emailAddress) {
        Concert concert = new Concert();
        concert.setEmailAddress(emailAddress);

        DynamoDBQueryExpression<Concert> queryExpression = new DynamoDBQueryExpression<Concert>()
                .withHashKeyValues(concert);

        return dynamoDbMapper.query(Concert.class, queryExpression);
    }

    /**
     * Retrieves all concerts matching provided emailAddress and bandName
     * <p>
     * If none found, returns an empty list.
     *
     * @param emailAddress The emailAddress to look up
     * @param bandName The bandName to look up
     * @return A list of Concerts found, if any
     */
    public List<Concert> getAllConcertsByBand(String emailAddress, String bandName) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":emailAddress", new AttributeValue().withS(emailAddress));
        valueMap.put(":bandName", new AttributeValue().withS(bandName));
        DynamoDBQueryExpression<Concert> queryExpression = new DynamoDBQueryExpression<Concert>()
                .withIndexName(BAND_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("emailAddress = :emailAddress and bandName = :bandName")
                .withExpressionAttributeValues(valueMap);

        return dynamoDbMapper.query(Concert.class, queryExpression);




//        GlobalSecondaryIndex SeenBandIndex = new GlobalSecondaryIndex()
//                .withIndexName(BAND_INDEX);
//                //.withProvisionedThroughput(new ProvisionedThroughput()
//                        .withReadCapacityUnits((long) 10)
//                        .withWriteCapacityUnits((long) 1))
//                .withProjection(new Projection().withProjectionType(ProjectionType.ALL));
//
//        ArrayList<KeySchemaElement> indexKeySchema = new ArrayList<KeySchemaElement>();
//
//        indexKeySchema.add(new KeySchemaElement()
//                .withAttributeName("Date")
//                .withKeyType(KeyType.HASH));  //Partition key
//        indexKeySchema.add(new KeySchemaElement()
//                .withAttributeName("Precipitation")
//                .withKeyType(KeyType.RANGE));  //Sort key
//
//        precipIndex.setKeySchema(indexKeySchema);
//
//        CreateTableRequest createTableRequest = new CreateTableRequest()
//                .withTableName("WeatherData")
//                .withProvisionedThroughput(new ProvisionedThroughput()
//                        .withReadCapacityUnits((long) 5)
//                        .withWriteCapacityUnits((long) 1))
//                .withAttributeDefinitions(attributeDefinitions)
//                .withKeySchema(tableKeySchema)
//                .withGlobalSecondaryIndexes(precipIndex);
//
//        Table table = dynamoDB.createTable(createTableRequest);
//        System.out.println(table.getDescription());
    }

    /**
     * Saves provided Concert to DynamoDB to create or update DynamoDB record.
     *
     * @param concert The Concert to be saved
     */
    public void writeProject(Concert concert) {
        dynamoDbMapper.save(concert);
    }

    /**
     * Removes the provided Concert from DynamoDB, if present.
     *
     * @param emailAddress The emailAddress of user
     * @param dateAttended The date of concert to be deleted
     */
    public void deleteConcert(String emailAddress, String dateAttended) {
        Concert concert = new Concert();
        concert.setEmailAddress(emailAddress);
        concert.setDateAttended(dateAttended);

        dynamoDbMapper.delete(concert);
    }
}

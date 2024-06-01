package com.nashss.se.concertmemories.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.concertmemories.dynamodb.models.Concert;
import com.nashss.se.concertmemories.exceptions.ConcertNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;

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
    public Concert getSingleProject(String emailAddress, String dateAttended) {
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
     * @param concert The Concert to be deleted
     */
    public void deleteConcert(Concert concert) {
        dynamoDbMapper.delete(concert);
    }
}

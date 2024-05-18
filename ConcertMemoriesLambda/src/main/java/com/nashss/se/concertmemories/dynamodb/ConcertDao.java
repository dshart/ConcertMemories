package com.nashss.se.concertmemories.dynamodb;

import com.nashss.se.concertmemories.dynamodb.models.Concert;
import com.nashss.se.concertmemories.exceptions.ConcertNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a playlist using {@link Concert} to represent the model in DynamoDB.
 */
@Singleton
public class ConcertDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a ConcertDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the concerts table
     */
    @Inject
    public ConcertDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Returns the {@link Concert} corresponding to the specified date.
     //     *
     //     * @param id the Concert date
     //     * @return the stored Concert, or null if none was found.
     //     */
    public Concert getConcert(String dateAttended) {
        Concert concert = this.dynamoDbMapper.load(Concert.class, dateAttended);

        if (concert == null) {
            throw new ConcertNotFoundException("Could not find concert on the date: " + dateAttended);
        }
        return concert;
    }

    /**
     * Saves (creates or updates) the given concdrt.
     * @param concert The concert to save
     * @return The Concert object that was saved
     */
    public Concert saveConcert(Concert concert) {
        this.dynamoDbMapper.save(concert);
        return concert;
    }
}

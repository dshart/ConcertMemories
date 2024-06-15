package com.nashss.se.concertmemories.api.concert.activity;

import com.nashss.se.concertmemories.api.concert.request.DeleteConcertRequest;
import com.nashss.se.concertmemories.api.concert.result.DeleteConcertResult;
import com.nashss.se.concertmemories.converters.ModelConverter;
import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import com.nashss.se.concertmemories.dynamodb.models.Concert;
import com.nashss.se.concertmemories.exceptions.InvalidAttributeValueException;
import com.nashss.se.concertmemories.models.ConcertModel;

import javax.inject.Inject;

/**
 * Implementation of ConcertActivity for Concert Memories' DeleteConcertActivity API
 *
 * This API allows a user to Delete a Single Concert object with emailAddress and dateAttended.
 */
public class DeleteConcertActivity {
    private final ConcertDao concertDao;

    /**
     * Instantiates a new DeleteConcertActivity object
     *
     * @param concertDao ConcertDao to interact with the concert table
     */

    @Inject
    public DeleteConcertActivity(ConcertDao concertDao)  {
        this.concertDao = concertDao;
    }

    /**
     * This method handles the request by accepting a DeleteConcertRequest object
     * and deletes the Concert object in the DB
     * @return {@link com.nashss.se.concertmemories.dynamodb.models.Concert}
     */
    public DeleteConcertResult handleRequest(final DeleteConcertRequest deleteConcertRequest) {
        if (deleteConcertRequest.getEmailAddress() == null || deleteConcertRequest.getDateAttended() == null) {
            throw new InvalidAttributeValueException("Error deleting Concert: requires emailAddress and dateAttended");
        }
        String emailAddress = deleteConcertRequest.getEmailAddress();
        String dateAttended = deleteConcertRequest.getDateAttended();
        Concert concert = concertDao.getConcert(emailAddress, dateAttended);
        concertDao.deleteConcert(emailAddress, dateAttended);

        ConcertModel concertModel = new ModelConverter().toConcertModel(concert);
        return DeleteConcertResult.builder()
            .withConcert(concertModel)
            .build();
    }
}

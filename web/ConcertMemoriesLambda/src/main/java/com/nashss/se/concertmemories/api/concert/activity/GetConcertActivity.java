package com.nashss.se.concertmemories.api.concert.activity;

import com.nashss.se.concertmemories.api.concert.request.GetConcertRequest;
import com.nashss.se.concertmemories.api.concert.result.GetConcertResult;
import com.nashss.se.concertmemories.converters.ModelConverter;
import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import com.nashss.se.concertmemories.dynamodb.models.Concert;
import com.nashss.se.concertmemories.models.ConcertModel;

import javax.inject.Inject;

/**
 * Implementation of the GetConcertActivity for the Concert Memories' GetConcert API.
 *
 * This API allows the customer to get one of their saved playlists.
 */
public class GetConcertActivity {
    //private final Logger log = LogManager.getLogger();
    private final ConcertDao concertDao;

    /**
     * Instantiates a new GetConcert object.
     *
     * @param concertDao ConcertDao to access the concert table.
     */
    @Inject
    public GetConcertActivity(ConcertDao concertDao) {
        this.concertDao = concertDao;
    }

    /**
     * This method handles the incoming request by retrieving the concert from the database.
     * <p>
     * It then returns the concert.
     * <p>
     * If the concert does not exist, this should throw a ConcertNotFoundException.
     *
     * @param getConcertRequest request object containing the email address and concert date
     * @return getConcertResult result object containing the API defined {@link ConcertModel}
     */
    public GetConcertResult handleRequest(final GetConcertRequest getConcertRequest) {

        String emailAddress = getConcertRequest.getEmailAddress();
        String dateAttended = getConcertRequest.getDateAttended();
        Concert concert = concertDao.getConcert(emailAddress, dateAttended);
        ConcertModel concertModel = new ModelConverter().toConcertModel(concert);

        return GetConcertResult.builder()
           .withConcert(concertModel)
           .build();
    }
}
package com.nashss.se.concertmemories.api.activity.concert;

import com.nashss.se.concertmemories.api.request.concert.GetConcertByDateRequest;
import com.nashss.se.concertmemories.api.result.concert.GetConcertByDateResult;
import com.nashss.se.concertmemories.converters.ModelConverter;
import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import com.nashss.se.concertmemories.dynamodb.models.Concert;
import com.nashss.se.concertmemories.models.ConcertModel;

import javax.inject.Inject;

/**
 * Implementation of the GetConcertByDateActivity for the ConcertMemories GetConcert API.
 *
 * This API allows the customer to get one of their saved concerts.
 */
public class GetConcertByDateActivity {
    private final ConcertDao concertDao;

    /**
     * Instantiates a new GetPlaylistActivity object.
     *
     * @param concertDao ConcertDao to access the concert table.
     */
    @Inject
    public GetConcertByDateActivity(ConcertDao concertDao) {
        this.concertDao = concertDao;
    }

    /**
     * This method handles the incoming request by retrieving the concert from the database.
     * <p>
     * It then returns the concert.
     * <p>
     * If the concert does not exist, this should throw a ConcertNotFoundException.
     *
     * @param getConcertByDateRequest request object containing the concert dateAttended
     * @return getConcertByDateResult result object containing the API defined {@link ConcertModel}
     */
    public GetConcertByDateResult handleRequest(final GetConcertByDateRequest getConcertByDateRequest) {
        //log.info("Received GetPlaylistRequest {}", getPlaylistRequest);
        String emailAddress = getConcertByDateRequest.getEmailAddress();
        String dateAttended = getConcertByDateRequest.getDateAttended();
        Concert concert = concertDao.getConcert(emailAddress, dateAttended);
        ConcertModel concertModel = new ModelConverter().toConcertModel(concert);

        return GetConcertByDateResult.builder()
                .withConcert(concertModel)
                .build();
    }
}

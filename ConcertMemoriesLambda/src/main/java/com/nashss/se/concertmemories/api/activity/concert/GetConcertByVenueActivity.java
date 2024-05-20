package com.nashss.se.concertmemories.api.activity.concert;

import com.nashss.se.concertmemories.api.request.concert.GetConcertByVenueRequest;
import com.nashss.se.concertmemories.api.result.concert.GetConcertByVenueResult;
import com.nashss.se.concertmemories.converters.ModelConverter;
import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import com.nashss.se.concertmemories.dynamodb.models.Concert;
import com.nashss.se.concertmemories.models.ConcertModel;

import javax.inject.Inject;

/**
 * Implementation of the GetConcertByVenueActivity for the ConcertMemories GetConcert API.
 *
 * This API allows the customer to get one of their saved concerts.
 */
public class GetConcertByVenueActivity {
    private final ConcertDao concertDao;

    /**
     * Instantiates a new GetConcert object.
     *
     * @param concertDao ConcertDao to access the concert table.
     */
    @Inject
    public GetConcertByVenueActivity(ConcertDao concertDao) {
        this.concertDao = concertDao;
    }

    /**
     * This method handles the incoming request by retrieving the concert from the database.
     * <p>
     * It then returns the concert.
     * <p>
     * If the concert does not exist, this should throw a ConcertNotFoundException.
     *
     * @param getConcertByVenueRequest request object containing the concert venue
     * @return getConcertByVenueResult result object containing the API defined {@link ConcertModel}
     */
    public GetConcertByVenueResult handleRequest(final GetConcertByVenueRequest getConcertByVenueRequest) {
        //log.info("Received GetConcertByVenueRequest {}", getConcertByVenueRequest);
        String emailAddress = getConcertByVenueRequest.getEmailAddress();
        String venue = getConcertByVenueRequest.getVenue();
        Concert concert = concertDao.getConcert(emailAddress, venue);
        ConcertModel concertModel = new ModelConverter().toConcertModel(concert);

        return GetConcertByVenueResult.builder()
                .withConcert(concertModel)
                .build();
    }
}
package com.nashss.se.concertmemories.api.activity.concert;

import com.nashss.se.concertmemories.api.request.concert.GetConcertByBandRequest;
import com.nashss.se.concertmemories.api.result.concert.GetConcertByBandResult;
import com.nashss.se.concertmemories.converters.ModelConverter;
import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import com.nashss.se.concertmemories.dynamodb.models.Concert;
import com.nashss.se.concertmemories.models.ConcertModel;

import javax.inject.Inject;

/**
 * Implementation of the GetConcertByBandActivity for the ConcertMemories GetConcert API.
 *
 * This API allows the customer to get one of their saved concerts.
 */
public class GetConcertByBandActivity {
    private final ConcertDao concertDao;

    /**
     * Instantiates a new GetConcert object.
     *
     * @param concertDao ConcertDao to access the concert table.
     */
    @Inject
    public GetConcertByBandActivity(ConcertDao concertDao) {
        this.concertDao = concertDao;
    }

    /**
     * This method handles the incoming request by retrieving the concert from the database.
     * <p>
     * It then returns the concert.
     * <p>
     * If the concert does not exist, this should throw a ConcertNotFoundException.
     *
     * @param getConcertByBandRequest request object containing the concert bandName
     * @return getConcertByBandResult result object containing the API defined {@link ConcertModel}
     */
    public GetConcertByBandResult handleRequest(final GetConcertByBandRequest getConcertByBandRequest) {
        //log.info("Received GetConcertByBandRequest {}", getConcertByBandRequest);
        String emailAddress = getConcertByBandRequest.getEmailAddress();
        String bandName = getConcertByBandRequest.getBandName();
        Concert concert = concertDao.getConcert(emailAddress, bandName);
        ConcertModel concertModel = new ModelConverter().toConcertModel(concert);

        return GetConcertByBandResult.builder()
                .withConcert(concertModel)
                .build();
    }
}



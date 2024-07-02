package com.nashss.se.concertmemories.api.concert.activity;

import com.nashss.se.concertmemories.api.concert.request.GetAllConcertsByVenueRequest;
import com.nashss.se.concertmemories.api.concert.result.GetAllConcertsByVenueResult;
import com.nashss.se.concertmemories.converters.ModelConverter;
import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import com.nashss.se.concertmemories.dynamodb.models.Concert;
import com.nashss.se.concertmemories.models.ConcertModel;

import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of GetAllConcertByVenueActivity for ConcertMemories' GetAllConcertsByVenue API.
 *
 * This API allows the user to get all of their saved concerts by venue.
 */
public class GetAllConcertsByVenueActivity {
    private final ConcertDao concertDao;

    /**
     * Instantiates a new GetAllConcertsByVenueActivity object.
     *
     * @param concertDao ConcertDao to access the concert table
     */
    @Inject
    public GetAllConcertsByVenueActivity(ConcertDao concertDao) {

        this.concertDao = concertDao;
    }

    /**
     * This method handles the incoming request by retrieving all concerts at a specific venue from the database. .
     * <p>
     * It then returns the venues in a list.
     * <p>
     * If the venue list does not exist, this should throw a ConcertNotFoundException.
     *
     * @param getAllConcertsByVenueRequest request object containing the user email address and name of venue
     * @return getAllConcertsByVenueResult result object containing the API defined {@link ConcertModel}
     */
    public GetAllConcertsByVenueResult handleRequest(final GetAllConcertsByVenueRequest getAllConcertsByVenueRequest) {
        List<Concert> concertList = concertDao.getAllConcertsByVenue(getAllConcertsByVenueRequest.getEmailAddress(), getAllConcertsByVenueRequest.getVenue());
        List<ConcertModel> concertModels = new ModelConverter().toConcertModelList(concertList);

        return GetAllConcertsByVenueResult.builder()
                .withConcertModelList(concertModels)
                .build();
    }
}


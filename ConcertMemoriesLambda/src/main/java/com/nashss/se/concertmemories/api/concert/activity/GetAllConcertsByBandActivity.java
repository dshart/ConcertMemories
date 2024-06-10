package com.nashss.se.concertmemories.api.concert.activity;

import com.nashss.se.concertmemories.api.concert.request.GetAllConcertsByBandRequest;
import com.nashss.se.concertmemories.api.concert.request.GetAllConcertsRequest;
import com.nashss.se.concertmemories.api.concert.result.GetAllConcertsByBandResult;
import com.nashss.se.concertmemories.api.concert.result.GetAllConcertsResult;
import com.nashss.se.concertmemories.converters.ModelConverter;
import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import com.nashss.se.concertmemories.dynamodb.models.Concert;
import com.nashss.se.concertmemories.models.ConcertModel;

import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of GetAllConcertByBandActivity for ConcertMemories' GetAllConcertsByBand API.
 *
 * This API allows the user to get all of their saved concerts by band.
 */
public class GetAllConcertsByBandActivity {
    private final ConcertDao concertDao;

    /**
     * Instantiates a new GetAllConcertsByBandActivity object.
     *
     * @param concertDao ConcertDao to access the concert table
     */
    @Inject
    public GetAllConcertsByBandActivity(ConcertDao concertDao) {

        this.concertDao = concertDao;
    }

    /**
     * This method handles the incoming request by retrieving all concerts by specific band from the database. .
     * <p>
     * It then returns the concerts in a list.
     * <p>
     * If the concert list does not exist, this should throw a ConcertNotFoundException.
     *
     * @param getAllConcertsByBandRequest request object containing the useer email address and name of band
     * @return getAllConcertsByBandResult result object containing the API defined {@link ConcertModel}
     */
    public GetAllConcertsByBandResult handleRequest(final GetAllConcertsByBandRequest getAllConcertsByBandRequest) {
        List<Concert> concertList = concertDao.getAllConcertsByBand(getAllConcertsByBandRequest.getEmailAddress(), getAllConcertsByBandRequest.getBandName());
        List<ConcertModel> concertModels = new ModelConverter().toConcertModelList(concertList);

        return GetAllConcertsByBandResult.builder()
                .withConcertModelList(concertModels)
                .build();
    }
}


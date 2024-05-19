package com.nashss.se.concertmemories.api.activity;

import com.nashss.se.concertmemories.api.request.GetAllConcertsRequest;
import com.nashss.se.concertmemories.api.result.GetAllConcertsResult;

import com.nashss.se.concertmemories.models.ConcertModel;

import com.nashss.se.concertmemories.converter.ModelConverter;
import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import com.nashss.se.concertmemories.dynamodb.models.Concert;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of GetConcertActivity for ConcertMemories' GetConcert API.
 *
 * This API allows the user to get one of their saved concerts.
 */
public class GetAllConcertsActivity {
    private final ConcertDao concertDao;

    /**
     * Instantiates a new GetAllConcertActivity object.
     *
     * @param concertDao ConcertDao to access the concert table.
     */
    @Inject
    public GetAllConcertsActivity(ConcertDao concertDao) {

        this.concertDao = concertDao;
    }

    /**
     * This method handles the incoming request by retrieving all concerts from the database.
     * <p>
     * It then returns the concert in a list.
     * <p>
     * If the concert list does not exist, this should throw a ConcertNotFoundException.
     *
     * @param getAllConcertRequest request object containing the concert date
     * @return getConcertResult result object containing the API defined {@link ConcertModel}
     */
    public GetAllConcertsResult handleRequest(final GetAllConcertsRequest getAllConcertsRequest) {
        List<ConcertModel> concertModelList = concertDao.getAllConcerts(getAllConcertsRequest.getDateAttended()
        String dateAttended = getConcertRequest.getDateAttended();
        Concert concert = concertDao.getConcert(getAllConcertsRequest.getDateAttended());
        List<ConcertModel> concertModels = new ModelConverter().toConcertModelList(concert.get(concert);

        return GetAllConcertsResult.builder()
                .withConcert(concertModel)
                .build();
    }
}


    /**
     * This method handles the request by retrieving all available concerts from the database
     * @return GetAllConcertsResults object containing a list of {@link com.nashss.se.concertmemories.dynamodb.models.ConcertModel}
     */
    public GetAllConcertsResult handleRequest(final GetAllConcertsRequest getAllProjectsRequest) {
        List<ConcertModel> concertModelList = concertDao.getAllProjects(getAllProjectsRequest.getEmailAddress());
        //for (ConcertModel concertModel : concertModelList) {
        //    project.setCompletionPercentage(this.calculateCompletion(project.getTaskList()));
       // }
        return GetAllConcertsResult.builder()
                .withConcertModelsList(concertModelList)
                .build();
    }
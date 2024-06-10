package com.nashss.se.concertmemories.api.concert.activity;

import com.nashss.se.concertmemories.api.concert.request.CreateConcertRequest;
import com.nashss.se.concertmemories.api.concert.result.CreateConcertResult;
import com.nashss.se.concertmemories.models.ConcertModel;

import com.nashss.se.concertmemories.converters.ModelConverter;
import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import com.nashss.se.concertmemories.dynamodb.models.Concert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the CreateConcertActivity for the Concert Memories web app CreateConcert API.
 * <p>
 * This API allows the customer to create a new concert
 */
public class CreateConcertActivity {
    private final Logger log = LogManager.getLogger();
    private final ConcertDao concertDao;

    /**
     * Instantiates a new CreateConcertActivity object.
     *
     * @param concertDao ConcertDao to access the concerts table.
     */
    @Inject
    public CreateConcertActivity(ConcertDao concertDao) {
        this.concertDao = concertDao;
    }

    /**
     * This method handles the incoming request by persisting a new concert
     * with the provided concert date and user email address from the request.
     * <p>
     * It then returns the newly created concert.
     * <p>
     *
     * @param createConcertRequest request object containing the concert name and user email address
     *                              associated with it
     * @return createConcertResult result object containing the API defined {@link ConcertModel}
     */
    public CreateConcertResult handleRequest(final CreateConcertRequest createConcertRequest) {
        log.info("Received CreateConcertRequest {}", createConcertRequest);

        List<String> openingActs = null;
        if (createConcertRequest.getOpeningActs() != null) {
            openingActs = new ArrayList<>(createConcertRequest.getOpeningActs());
        }

        List<String> songsPlayed = null;
        if (createConcertRequest.getSongsPlayed() != null) {
            songsPlayed = new ArrayList<>(createConcertRequest.getSongsPlayed());
        }

        List<String> memories = null;
        if (createConcertRequest.getMemories() != null) {
            memories = new ArrayList<>(createConcertRequest.getMemories());
        }

        Concert concert = new Concert();
        concert.setEmailAddress(createConcertRequest.getEmailAddress());
        concert.setDateAttended(createConcertRequest.getDateAttended());
        concert.setBandName(createConcertRequest.getBandName());
        concert.setTourName(createConcertRequest.getTourName());
        concert.setVenue(createConcertRequest.getVenue());
        concert.setOpeningActs(createConcertRequest.getOpeningActs());
        concert.setSongsPlayed(createConcertRequest.getSongsPlayed());
        concert.setMemories(createConcertRequest.getMemories());

        concertDao.saveConcert(concert);

        ConcertModel concertModel = new ModelConverter().toConcertModel(concert);
        return CreateConcertResult.builder()
                .withConcert(concertModel)
                .build();
    }
}
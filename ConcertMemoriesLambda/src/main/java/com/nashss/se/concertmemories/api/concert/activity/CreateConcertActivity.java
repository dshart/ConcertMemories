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

//        List<String> openingActs = null;
//        if (createConcertRequest.getOpeningActs() != null) {
//            openingActs = new ArrayList<>(createConcertRequest.getOpeningActs());
//        }
//
//        List<String> songsPlayed = null;
//        if (createConcertRequest.getSongsPlayed() != null) {
//            songsPlayed = new ArrayList<>(createConcertRequest.getSongsPlayed());
//        }
//
//        List<String> memories = null;
//        if (createConcertRequest.getMemories() != null) {
//            memories = new ArrayList<>(createConcertRequest.getMemories());
//        }

        Concert concert = new Concert();

        String emailAddress = createConcertRequest.getEmailAddress();
        if (emailAddress == null) {
            emailAddress = " ";
        }
        concert.setEmailAddress(emailAddress);

        String dateAttended = createConcertRequest.getDateAttended();
        if (dateAttended == null) {
            dateAttended = " ";
        }
        concert.setDateAttended(dateAttended);

        String bandName = createConcertRequest.getBandName();
        if (bandName == null) {
            bandName = " ";
        }
        concert.setBandName(bandName);

        String tourName = createConcertRequest.getTourName();
        if (tourName == null) {
            tourName = " ";
        }
        concert.setTourName(tourName);

        String venue = createConcertRequest.getVenue();
        if (venue == null) {
            venue = " ";
        }
        concert.setVenue(venue);

        List<String> openingActs = createConcertRequest.getOpeningActs();
        if (openingActs == null) {
            openingActs = new ArrayList<>();
        }
        concert.setOpeningActs(openingActs);

        List<String> songsPlayed = createConcertRequest.getSongsPlayed();
        if (songsPlayed == null) {
            songsPlayed = new ArrayList<>();
        }
        concert.setSongsPlayed(songsPlayed);

        List<String> memories = createConcertRequest.getMemories();
        if (memories == null) {
            memories = new ArrayList<>();
        }
        concert.setMemories(memories);

        concertDao.saveConcert(concert);

        ConcertModel concertModel = new ModelConverter().toConcertModel(concert);
        return CreateConcertResult.builder()
                .withConcert(concertModel)
                .build();
    }
}
//package com.nashss.se.concertmemories.api.concert.activity;
//
//import com.nashss.se.concertmemories.api.concert.request.UpdateConcertRequest;
//import com.nashss.se.concertmemories.api.concert.result.CreateConcertResult;
//import com.nashss.se.concertmemories.api.concert.result.UpdateConcertResult;
//import com.nashss.se.concertmemories.models.ConcertModel;
//import com.nashss.se.concertmemories.converters.ModelConverter;
//import com.nashss.se.concertmemories.dynamodb.ConcertDao;
//import com.nashss.se.concertmemories.dynamodb.models.Concert
//import com.nashss.se.concertmemories.exceptions.InvalidAttributeValueException;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.inject.Inject;
//
///**
// * Implementation of the UpdateConcertActivity for the Concert Memories' web app UpdateConcert API.
// *
// * This API allows the customer to update their saved concert information.
// */
//public class UpdateConcertActivity {
//    private final Logger log = LogManager.getLogger();
//    private final ConcertDao concertDao;
//
//    /**
//     * Instantiates a new UpdateConcertActivity object.
//     *
//     * @param concertDao ConcertDao to access the concert table.
//     *
//     */
//    @Inject
//    public UpdateConcertActivity(ConcertDao concertDao) {
//       this.concertDao = concertDao;
//    }
//
//    /**
//     * This method handles the incoming request by retrieving the concert, updating it,
//     * and persisting the concert.
//     * <p>
//     * It then returns the updated concert.
//     * <p>
//     * If the concert does not exist, this should throw a ConcertNotFoundException.
//     * <p>
//     * * @param updateConcertRequest request object containing emailAddress and dateAttended for concert to update
//     *
//     * @return updateConcertResult result object containing the API defined {@link ConcertModel}
//     */
//    public UpdateConcertResult handleRequest ( final UpdateConcertRequest updateConcertRequest){
//        Concert concert = concertDao.getConcert(updateConcertRequest.getEmailAddress(), updateConcertRequest.getDateAttended());
//        concert.setDateAttended(updateConcertRequest.getDateAttended());
//        concert.setBandName(updateConcertRequest.getBandName());
//        concert.setTourName(updateConcertRequest.getTourName());
//        concert.setVenue(updateConcertRequest.getVenue());
//        concert.setOpeningActs(updateConcertRequest.getOpeningActs());
//        concert.setSongsPlayed(updateConcertRequest.getSongsPlayed());
//        concert.setMemories(updateConcertRequest.getMemories());
//
//        ConcertModel concertModel = new ModelConverter().toConcertModel(concert);
//        return UpdateConcertResult.builder()
//            .withConcert(concertModel)
//            .build();
//    }
//}


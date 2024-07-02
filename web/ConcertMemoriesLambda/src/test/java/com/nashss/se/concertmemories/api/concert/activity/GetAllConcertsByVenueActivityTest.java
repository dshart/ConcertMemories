//package com.nashss.se.concertmemories.api.concert.activity;
//
//import com.nashss.se.concertmemories.api.concert.activity.GetAllConcertsByVenueActivity;
//import com.nashss.se.concertmemories.api.concert.request.GetAllConcertsByVenueRequest;
//import com.nashss.se.concertmemories.api.concert.result.GetAllConcertsByVenueResult;
//import com.nashss.se.concertmemories.converters.ModelConverter;
//import com.nashss.se.concertmemories.dynamodb.ConcertDao;
//import com.nashss.se.concertmemories.dynamodb.models.Concert;
//import com.nashss.se.concertmemories.models.ConcertModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.openMocks;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class GetAllConcertsByVenueActivityTest {
//    @Mock
//    private ConcertDao concertDao;
//
//    private GetAllConcertsByVenueActivity getAllConcertsByVenueActivity;
//    List<Concert> redRocksConcertsList;
//    List<ConcertModel> redRocksConcertModelsList;
//
//    @BeforeEach
//    void setup() {
//        openMocks(this);
//        getAllConcertsByVenueActivity = new GetAllConcertsByVenueActivity(concertDao);
//
//        Concert concert1 = new Concert();
//        Concert concert2 = new Concert();
//        Concert concert3 = new Concert();
//        Concert concert4 = new Concert();
//        concert1.setEmailAddress("concertFan@google.com");
//        concert1.setDateAttended("1999-09-19");
//        concert1.setBandName("Cyndi Lauper");
//        concert1.setTourName("Girls Just Wanna Have Fun");
//        concert1.setVenue("Red Rocks");
//        concert2.setEmailAddress("concertFan@google.com");
//        concert2.setDateAttended("1999-04-05");
//        concert2.setBandName("Madonna");
//        concert2.setTourName("Like a Virgin");
//        concert2.setVenue("Red Rocks");
//        concert3.setEmailAddress("concertFan@google.com");
//        concert3.setDateAttended("1987-09-19");
//        concert3.setBandName("Cyndi Lauper");
//        concert3.setTourName("Take Me Home Tonight");
//        concert3.setVenue("Fleetcoor Center");
//        concert4.setEmailAddress("concertFan@google.com");
//        concert4.setDateAttended("1966-08-22");
//        concert4.setBandName("Beatles");
//        concert4.setTourName("Revovler Tour");
//        concert4.setVenue("Shea Stadium");
//
//        redRocksConcertsList = new ArrayList<>();
//        redRocksConcertsList.add(concert1);
//        redRocksConcertsList.add(concert2);
//        redRocksConcertModelsList = new ModelConverter().toConcertModelList(redRocksConcertsList);
//    }
//
//    @Test
//    void handleRequest_atLeastOneConcertForVenueExistsForEmail_returnsIsOfTypeList() {
//        // GIVEN
//
//        String email = "concertFan@google.com";
//        GetAllConcertsByVenueRequest request = GetAllConcertsByVenueRequest.builder()
//                .withEmailAddress(email)
//                .withVenue("Red Rocks")
//                .build();
//        when(concertDao.getAllConcertsByVenue(email, request.getVenue())).thenReturn(redRocksConcertsList);
//
//        // WHEN
//        GetAllConcertsByVenueResult concertResultsList = getAllConcertsByVenueActivity.handleRequest(request);
//
//        // THEN
//        //assert a list is returned
//        assertEquals(concertResultsList.getAllConcertsByVenue().getClass(), ArrayList.class);
//    }
//
//    @Test
//    void handleRequest_concertsExist_returnsExpectedNumberOfVenueConcertsForEmailInList() {
//        // GIVEN
//
//        String email = "concertFan@google.com";
//        GetAllConcertsByVenueRequest request = GetAllConcertsByVenueRequest.builder()
//                .withEmailAddress(email)
//                .withVenue("Red Rocks")
//                .build();
//        when(concertDao.getAllConcertsByVenue(email, request.getVenue())).thenReturn(redRocksConcertsList);
//
//        // WHEN
//        GetAllConcertsByVenueResult concertResultsList = getAllConcertsByVenueActivity.handleRequest(request);
//
//        // THEN
//        ///same number of items
//        assertEquals(redRocksConcertModelsList.size(), concertResultsList.getAllConcertsByVenue().size());
//    }
//
//    @Test
//    void handleRequest_noConcertsinDBForVenue_returnsEmptyList() {
//        // GIVEN
//        List<Concert> emptyList = new ArrayList<>();
//
//        String email = "email@bogus.com";
//        GetAllConcertsByVenueRequest request = GetAllConcertsByVenueRequest.builder()
//                .withEmailAddress(email)
//                .withVenue("Lopers Gateway Arena")
//                .build();
//
//        when(concertDao.getAllConcertsByVenue(email, request.getVenue())).thenReturn(emptyList);
//
//        // WHEN
//        GetAllConcertsByVenueResult concertResultsList = getAllConcertsByVenueActivity.handleRequest(request);
//
//        assertTrue(concertResultsList.getAllConcertsByVenue().isEmpty(),
//                "Expected concert list to be empty but was not.");
//    }
//
//    @Test
//    void handleRequest_databaseHasConcertsForVenue_returnsConcertsInDatabase() {
//        // GIVEN
//        String email = "concertFan@google.com";
//        GetAllConcertsByVenueRequest request = GetAllConcertsByVenueRequest.builder()
//                .withEmailAddress(email)
//                .withVenue("Red Rocks")
//                .build();
//        when(concertDao.getAllConcertsByVenue(email, request.getVenue())).thenReturn(redRocksConcertsList);
//
//
//        // WHEN
//        GetAllConcertsByVenueResult result = getAllConcertsByVenueActivity.handleRequest(request);
//
//        // THEN
//        for (ConcertModel cm : redRocksConcertModelsList) {
//            assertEquals(cm.getVenue(), "Red Rocks");
//        }
//    }
//}

//Other tests left to do - no time
//test for no email address returns empty list
//test for no band returns empty list
//test for email address not valid returns empty list
//test for ... I'm sure there are others}
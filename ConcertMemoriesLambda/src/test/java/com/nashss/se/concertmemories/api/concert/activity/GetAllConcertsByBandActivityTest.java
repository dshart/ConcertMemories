//package com.nashss.se.concertmemories.api.concert.activity;
//
//import com.nashss.se.concertmemories.api.concert.activity.GetAllConcertsByBandActivity;
//import com.nashss.se.concertmemories.api.concert.request.GetAllConcertsByBandRequest;
//import com.nashss.se.concertmemories.api.concert.result.GetAllConcertsByBandResult;
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
//public class GetAllConcertsByBandActivityTest {
//    @Mock
//    private ConcertDao concertDao;
//
//    private GetAllConcertsByBandActivity getAllConcertsByBandActivity;
//    List<Concert> cyndiLauperConcertsList;
//    List<ConcertModel> cyndiLauperConcertModelsList;
//
//    @BeforeEach
//    void setup() {
//        openMocks(this);
//        getAllConcertsByBandActivity = new GetAllConcertsByBandActivity(concertDao);
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
//        cyndiLauperConcertsList = new ArrayList<>();
//        cyndiLauperConcertsList.add(concert1);
//        cyndiLauperConcertsList.add(concert3);
//        cyndiLauperConcertModelsList = new ModelConverter().toConcertModelList(cyndiLauperConcertsList);
//    }
//
//    @Test
//    void handleRequest_atLeastOneConcertForBandExistsForEmail_returnsIsOfTypeList() {
//        // GIVEN
//
//        String email = "concertFan@google.com";
//        GetAllConcertsByBandRequest request = GetAllConcertsByBandRequest.builder()
//                .withEmailAddress(email)
//                .withBandName("Cyndi Lauper")
//                .build();
//        when(concertDao.getAllConcertsByBand(email, request.getBandName())).thenReturn(cyndiLauperConcertsList);
//
//        // WHEN
//        GetAllConcertsByBandResult concertResultsList = getAllConcertsByBandActivity.handleRequest(request);
//
//        // THEN
//        //assert a list is returned
//        assertEquals(concertResultsList.getAllConcertsByBand().getClass(), ArrayList.class);
//    }
//
//    @Test
//    void handleRequest_concertsExist_returnsExpectedNumberOfBandConcertsForEmailInList() {
//        // GIVEN
//
//        String email = "concertFan@google.com";
//        GetAllConcertsByBandRequest request = GetAllConcertsByBandRequest.builder()
//                .withEmailAddress(email)
//                .withBandName("Cyndi Lauper")
//                .build();
//        when(concertDao.getAllConcertsByBand(email, request.getBandName())).thenReturn(cyndiLauperConcertsList);
//
//        // WHEN
//        GetAllConcertsByBandResult concertResultsList = getAllConcertsByBandActivity.handleRequest(request);
//
//        // THEN
//        ///same number of items
//        assertEquals(cyndiLauperConcertModelsList.size(), concertResultsList.getAllConcertsByBand().size());
//    }
//
//    @Test
//    void handleRequest_noConcertsinDBForBand_returnsEmptyList() {
//        // GIVEN
//        List<Concert> emptyList = new ArrayList<>();
//
//        String email = "email@bogus.com";
//        GetAllConcertsByBandRequest request = GetAllConcertsByBandRequest.builder()
//                .withEmailAddress(email)
//                .withBandName("Stryper")
//                .build();
//
//        when(concertDao.getAllConcertsByBand(email, request.getBandName())).thenReturn(emptyList);
//
//        // WHEN
//        GetAllConcertsByBandResult concertResultsList = getAllConcertsByBandActivity.handleRequest(request);
//
//        assertTrue(concertResultsList.getAllConcertsByBand().isEmpty(),
//                "Expected concert list to be empty but was not.");
//    }
//
//    @Test
//    void handleRequest_databaseHasConcertsForBand_returnsConcertsInDatabase() {
//        // GIVEN
//        String email = "concertFan@google.com";
//        GetAllConcertsByBandRequest request = GetAllConcertsByBandRequest.builder()
//                .withEmailAddress(email)
//                .withBandName("Cyndi Lauper")
//                .build();
//        when(concertDao.getAllConcertsByBand(email, request.getBandName())).thenReturn(cyndiLauperConcertsList);
//
//
//        // WHEN
//        GetAllConcertsByBandResult result = getAllConcertsByBandActivity.handleRequest(request);
//
//        // THEN
//        for (ConcertModel cm : cyndiLauperConcertModelsList) {
//            assertEquals(cm.getBandName(), "Cyndi Lauper");
//        }
//    }
//}
//
////Other tests left to do - no time
//    //test for no email address returns empty list
//    //test for no band returns empty list
//    //test for email address not valid returns empty list
//    //test for ... I'm sure there are others}

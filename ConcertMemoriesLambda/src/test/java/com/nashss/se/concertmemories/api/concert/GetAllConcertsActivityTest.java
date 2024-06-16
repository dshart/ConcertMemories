package com.nashss.se.concertmemories.api.concert;

import com.nashss.se.concertmemories.api.concert.activity.GetAllConcertsActivity;
import com.nashss.se.concertmemories.api.concert.request.GetAllConcertsRequest;
import com.nashss.se.concertmemories.api.concert.result.GetAllConcertsResult;
import com.nashss.se.concertmemories.converters.ModelConverter;
import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import com.nashss.se.concertmemories.dynamodb.models.Concert;
import com.nashss.se.concertmemories.models.ConcertModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetAllConcertsActivityTest {
    @Mock
    private ConcertDao concertDao;

    private GetAllConcertsActivity getAllConcertsActivity;
    List<Concert> myConcertsList;
    List<ConcertModel> myConcertModelsList;

    @BeforeEach
    void setup() {
        openMocks(this);
        getAllConcertsActivity = new GetAllConcertsActivity(concertDao);

        Concert concert1 = new Concert();
        Concert concert2 = new Concert();
        Concert concert3 = new Concert();
        Concert concert4 = new Concert();
        concert1.setEmailAddress("concertFan@google.com");
        concert1.setDateAttended("1999-09-19");
        concert1.setBandName("Cyndi Lauper");
        concert1.setTourName("Girls Just Wanna Have Fun");
        concert1.setVenue("Red Rocks");
        concert2.setEmailAddress("concertFan@google.com");
        concert2.setDateAttended("1999-04-05");
        concert2.setBandName("Madonna");
        concert2.setTourName("Like a Virgin");
        concert2.setVenue("Red Rocks");
        concert3.setEmailAddress("concertFan@google.com");
        concert3.setDateAttended("1987-09-19");
        concert3.setBandName("Eddie Money");
        concert3.setTourName("Take Me Home Tonight");
        concert3.setVenue("Fleetcoor Center");
        concert4.setEmailAddress("concertFan@google.com");
        concert4.setDateAttended("1966-08-22");
        concert4.setBandName("Beatles");
        concert4.setTourName("Revovler Tour");
        concert4.setVenue("Shea Stadium");

        myConcertsList = new ArrayList<>();
        myConcertsList.add(concert1);
        myConcertsList.add(concert2);
        myConcertsList.add(concert3);
        myConcertsList.add(concert4);
        myConcertModelsList = new ModelConverter().toConcertModelList(myConcertsList);
    }

    @Test
    void handleRequest_atLeastOneConcertExistsForEmail_returnsIsOfTypeList() {
        // GIVEN

        String email = "concertFan@google.com";
        GetAllConcertsRequest request = GetAllConcertsRequest.builder()
                .withEmailAddress(email)
                .build();
        when(concertDao.getAllConcerts(email)).thenReturn(myConcertsList);

        // WHEN
        GetAllConcertsResult concertResultsList = getAllConcertsActivity.handleRequest(request);

        // THEN
        //assert a list is returned
        assertEquals(concertResultsList.getAllConcerts().getClass(), ArrayList.class);
    }

    @Test
    void handleRequest_concertsExist_returnsExpectedNumberOfConcertsForEmailInList() {
        // GIVEN

        String email = "concertFan@google.com";
        GetAllConcertsRequest request = GetAllConcertsRequest.builder()
                .withEmailAddress(email)
                .build();
        when(concertDao.getAllConcerts(email)).thenReturn(myConcertsList);


        // WHEN
        GetAllConcertsResult concertResultsList = getAllConcertsActivity.handleRequest(request);

        // THEN
        ///same number of items
        assertEquals(myConcertModelsList.size(), concertResultsList.getAllConcerts().size());
        //myConcertModelsList = new ModelConverter().toConcertModelList(myConcertsList);
    }

    @Test
    void handleRequest_noConcertsinDB_returnsEmptyList() {
        // GIVEN
        List<Concert> emptyList = new ArrayList<>();

        String email = "email@bogus.com";
        GetAllConcertsRequest request = GetAllConcertsRequest.builder()
                .withEmailAddress(email)
                .build();

        when(concertDao.getAllConcerts(email)).thenReturn(emptyList);

        // WHEN
        GetAllConcertsResult concertResultsList = getAllConcertsActivity.handleRequest(request);

        assertTrue(concertResultsList.getAllConcerts().isEmpty(),
                "Expected concert list to be empty but was not.");
    }

    @Test
    void handleRequest_databasehasConcerts_returnsConcertsInDatabase() {
        // GIVEN
        String email = "concertFan@google.com";
        GetAllConcertsRequest request = GetAllConcertsRequest.builder()
                .withEmailAddress(email)
                .build();
        when(concertDao.getAllConcerts(email)).thenReturn(myConcertsList);


        // WHEN
        GetAllConcertsResult result = getAllConcertsActivity.handleRequest(request);

        // THEN
        assertEquals(myConcertModelsList, result.getAllConcerts());
    }

//Other tests left to do - no time
  //test for sorted by date
  //test for sorted by band
 //test for sorted by venue
 //test for no email address returns empty list
 //test for same concert for 2 different email addresses returns only the specific email address
 //test for email address not valid returns empty list
 //test for ... I'm sure there are others
}


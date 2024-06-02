package com.nashss.se.concertmemories.api.concert;

import com.nashss.se.concertmemories.api.concert.activity.GetAllConcertsActivity;
import com.nashss.se.concertmemories.api.concert.request.GetAllConcertsRequest;
import com.nashss.se.concertmemories.api.concert.result.GetAllConcertsResult;
import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import com.nashss.se.concertmemories.dynamodb.models.Concert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetAllConcertsActivityTest {
    @Mock
    private ConcertDao concertDao;

    private GetAllConcertsActivity getAllConcertsActivity;
    List<Concert> myConcertsList;

    @BeforeEach
    void setup() {
        openMocks(this);
        getAllConcertsActivity = new GetAllConcertsActivity(concertDao);

        Concert concert1 = new Concert();
        Concert concert2 = new Concert();
        Concert concert3 = new Concert();
        concert1.setEmailAddress("noMatter@blimey.com");
        concert1.setDateAttended("1999-09-19");
        concert1.setBandName("Cyndi Lauper");
        concert1.setTourName("Girls Just Wanna Have Fun");
        concert1.setVenue("Red Rocks");
        concert2.setEmailAddress("noMatter@blimey.com");
        concert2.setDateAttended("1999-09-19");
        concert2.setBandName("Cyndi Lauper");
        concert2.setTourName("Girls Just Wanna Have Fun");
        concert2.setVenue("Red Rocks");
        concert3.setEmailAddress("noMatter@blimey.com");
        concert3.setDateAttended("1999-09-19");
        concert3.setBandName("Cyndi Lauper");
        concert3.setTourName("Girls Just Wanna Have Fun");
        concert3.setVenue("Red Rocks");

        myConcertsList = new ArrayList<>();
        myConcertsList.add(concert1);
        myConcertsList.add(concert2);
        myConcertsList.add(concert3);
    }

    @Test
    void handleRequest_atleastoneconcertExists_returnsconcertinlist() {
        // GIVEN

        GetAllConcertsRequest request = GetAllConcertsRequest.builder()
                .build();
        when(concertDao.getAllConcerts(any())).thenReturn(myConcertsList);

        // WHEN
        GetAllConcertsResult concertResultsList = getAllConcertsActivity.handleRequest(request);

        // THEN
        //assert a list is returned
        assertEquals(concertResultsList.getAllConcerts().getClass(), ArrayList.class);
        //   assertThat(concertResultsList.getAllConcerts() instanceOf(List.class));

    }

    @Test
    void handleRequest_concertsExist_returnsExpectedNumberOfConcertsInList() {
        // GIVEN

        GetAllConcertsRequest request = GetAllConcertsRequest.builder()
                .build();
        when(concertDao.getAllConcerts(any())).thenReturn(myConcertsList);


        // WHEN
        GetAllConcertsResult concertResultsList = getAllConcertsActivity.handleRequest(request);

        // THEN
        ///same number of items
        assertEquals(myConcertsList.size(), concertResultsList.getAllConcerts().size());
    }
}

//Other tests left to do
  //test for sorted by date
  //test for sorted by band
  //test for sorted by venue
  //test for no email address returns null
  //test for 2 email addresses returns the correct info for the provided email address
  //test for email address not existing returns "No concerts found" message
  //test for ...
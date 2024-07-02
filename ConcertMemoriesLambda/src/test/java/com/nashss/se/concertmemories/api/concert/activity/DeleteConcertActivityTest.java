//package com.nashss.se.concertmemories.api.concert.activity;
//
//import com.nashss.se.concertmemories.api.concert.request.DeleteConcertRequest;
//import com.nashss.se.concertmemories.api.concert.result.DeleteConcertResult;
//import com.nashss.se.concertmemories.dynamodb.ConcertDao;
//import com.nashss.se.concertmemories.models.Concert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//public class DeleteConcertActivityTest {
//    @Mock
//    private ConcertDao concdrtDao;
//
//    private DeleteConccertActivity activity;
//
//    @BeforeEach
//    public void setUp() {
//        openMocks(this);
//        activity = new DeleteConcertActivity(concertDao);
//    }
//
//    @Test
//    public void handleRequest_goodRequest_callsDaoDeleteMethod() {
//        // GIVEN
//        String emailAddress = "emailAddress";
//        String dateAttended = "date";
//        Concert concert = new Concert();
//        DeleteConcertRequest request = DeleteConcertRequest.builder()
//                .withEmailAddress((emailAddress)
//                .withDateAttended(dateAttended)
//                .build();
//        ArgumentCaptor<Concert> argumentCaptor = ArgumentCaptor.forClass(Concert.class);
//        // WHEN
//        DeleteConcertResult result = activity.handleRequest(request);
//
//        // THEN
//        verify(concertDao).deleteTask(argumentCaptor.capture());
//        assertEquals(emailAddress, argumentCaptor.getValue().getEmailAddress(), "Expected method to call Dao with values matching those provided in request");
//        assertEquals(dateAttended, argumentCaptor.getValue().dateAttended(), "Expected method to call Dao with values matching those provided in request");
//        assertEquals(emailAddress, result.getConcert().getEmailAddress(), "Expected method to output result with values matching those provided in request");
//        assertEquals(dateAttended, result.getConcert().getDateAttended(), "Expected method to output result with values matching those provided in request");
//    }
//}



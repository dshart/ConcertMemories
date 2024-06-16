//package com.nashss.se.musicplaylistservice.activity;
//
//import com.nashss.se.concertmemories.api.concert.activity.DeleteConcertActivity;
//import com.nashss.se.concertmemories.api.concert.request.DeleteConcertRequest;
//import com.nashss.se.concertmemories.api.concert.result.DeleteConcertResult;
//import com.nashss.se.concertmemories.dynamodb.ConcertDao;
//import com.nashss.se.concertmemories.dynamodb.models.Concert;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//public class DeleteConcertActivityTest {
//
//    @Mock
//    private ConcertDao concertDao;
//
//    private DeleteConcertActivity deleteConcertActivity;
//
//    @BeforeEach
//    public void setUp() {
//        openMocks(this);
//        deleteConcertActivity = new DeleteConcertActivity(concertDao);
//    }
//
//    @Test
//    public void handleRequest_goodRequest_callsDaoLoadMethod() {
//        // GIVEN
//        Concert concert = new Concert();
//
//        String emailAddress = "test@test.com";
//        String dateAttended = "1999-09-19";
//        DeleteConcertRequest request = DeleteConcertRequest.builder()
//                .withEmailAddress(emailAddress)
//                .withDateAttended(dateAttended)
//                .build();
//
//
//        when(concertDao.deleteConcert(request.getEmailAddress(), request.getDateAttended())).thenThrow();
//        doThrow(concertDao.deleteConcert(request.getEmailAddress(), request.getDateAttended()));
//
//
//        // WHEN
//        DeleteConcertResult result = deleteConcertActivity.handleRequest(request);
//
//        //THEN
//        verify(concertDao).deleteConcert(any(), any());
//    }
//}


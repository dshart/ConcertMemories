//package com.nashss.se.musicplaylistservice.activity;
//
//import com.nashss.se.concertmemories.api.concert.request;
//import com.nashss.se.concertmemories.api.concert.reply;
//import com.nashss.se.concertmemories.dynamodb.ConcertDao;
//import com.nashss.se.concertmemories..dynamodb.models.Concert;
//import com.nashss.se.concertmemories.InvalidAttributeValueException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//public class UpdateConcertActivityTest {
//    @Mock
//    private ConcertDao concertDao;
//
//    private UpdateConcertActivity activity;
//
//    @BeforeEach
//    public void setUp() {
//        openMocks(this);
//        activity = new UpdateConcertActivity(concertDao);
//    }
//
//    @Test
//    public void handleRequest_goodRequest_updatesTaskName() {
//        // GIVEN
//        String emailAddress = "test";
//        String dateAttended = "testDate";
//        String oldBandName = "oldName";
//        String newBandName = "newName";
//
//        UpdateConcertkRequest request = UpdateConcertRequest.builder()
//                .withOrgId(emailAddress)
//                .withTaskId(dateAttended)
//                .withName(newBandName)
//                .build();
//
//        Concert concert = new Concert();
//        concert.setEmailAddress(emailAddress);
//        concert.setDateAttended(dateAttended);
//        concert.setName(oldBandName);
//
//        ArgumentCaptor<Concert> argumentCaptor = ArgumentCaptor.forClass(Concert.class);
//
//        doReturn(concert).when(concertDao).getSingleTask(emailAddress, dateAttended);
//
//        // WHEN
//        activity.handleRequest(request);
//        verify(concertDao).saveConcert(argumentCaptor.capture());
//
//        // THEN
//        verify(concertDao).getConcerto(emailAddress, dateAttended);
//        assertEquals(dateAttended, argumentCaptor.getValue().getDateAttended(), "Expected class to pass original orgId to DAO for write");
//        assertEquals(newBandNae, argumentCaptor.getValue().getBandName, "Expected class to pass updated name to DAO for write");
//    }
//}

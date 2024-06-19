//package com.nashss.se.musicplaylistservice.dynamodb;
//
//import com.nashss.se.concertmemories.dynamodb.ConcertDao;
//import com.nashss.se.concertmemories.dynamodb.models.Concert;
//import com.nashss.se.concertmemories.exceptions.ConcertNotFoundException;
//
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.anyDouble;
//import static org.mockito.Mockito.eq;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.initMocks;
//
//public class ConcertDaoTest {
//    @Mock
//    private DynamoDBMapper dynamoDBMapper;
//    private ConcertDao concertDao;
//
//    @BeforeEach
//    public void setup() {
//        initMocks(this);
//        concertDao = new ConcertDao(dynamoDBMapper);
//    }
//
//    @Test
//    public void getAllConcdrts_returnsListOfConcerts() {
//        // GIVEN
//        Concert concert = new Concert();
//
//        // WHEN
//        Concert result = concertDao.saveConcert(concert);
//
//        // THEN
//        verify(dynamoDBMapper).save(concert);
//        assertEquals(concert, result);
//    }
//
//    @Test
//    public void getAllConcertsByBand_withValidBandname_returnsListOfConcertsByBand() {
//        // GIVEN
//        Concert concert = new Concert();
//
//        // WHEN
//        Concert result = concertDao.saveConcert(concert);
//
//        // THEN
//        verify(dynamoDBMapper).save(concert);
//        assertEquals(concert, result);
//    }
//
//    @Test
//    public void getAllConcertsByBand_withBogusBandname_returnsNull() {
//        // GIVEN
//        Concert concert = new Concert();
//
//        // WHEN
//        Concert result = concertDao.saveConcert(concert);
//
//        // THEN
//        verify(dynamoDBMapper).save(concert);
//        assertEquals(concert, result);
//    }
//
//    @Test
//    public void getAllConcertsByVenue_returnsListOfConcertsByVenue() {
//        // GIVEN
//        Concert concert = new Concert();
//
//        // WHEN
//        Concert result = concertDao.saveConcert(concert);
//
//        // THEN
//        verify(dynamoDBMapper).save(concert);
//        assertEquals(concert, result);
//    }
//
//    @Test
//    public void getAllConcertsByVenue_withBogusVenue_returnsNull() {
//        // GIVEN
//        Concert concert = new Concert();
//
//        // WHEN
//        Concert result = concertDao.saveConcert(concert);
//
//        // THEN
//        verify(dynamoDBMapper).save(concert);
//        assertEquals(concert, result);
//    }
//
//
//    @Test
//    public void getConcert_withdateAttended_callsMapperWithDateAttended() {
//        // GIVEN
//        String emailAddress = "test@test.com";
//        String dateAttended = "1999-09-09";
//        when(dynamoDBMapper.load(Concert.class, dateAttended)).thenReturn(new Concert());
//
//        // WHEN
//        Concert concert = concertDao.getConcert(emailAddress, dateAttended);
//
//        // THEN
//        assertNotNull(concert);
//        verify(dynamoDBMapper).load(Concert.class, dateAttended);
//    }
//
//    @Test
//    public void getConcert_concertNotFound_throwsConcertNotFoundException() {
//        // GIVEN
//        String nonexistentConcertDate = "12-12-1822";
//        when(dynamoDBMapper.load(Concert.class, nonexistentConcertDate)).thenReturn(null);
//
//        // WHEN + THEN
//        assertThrows(ConcertNotFoundException.class, () -> concertDao.getConcert(nonexistentConcertDate));
//    }
//
//    @Test
//    public void saveConcert_callsMapperWithConcert() {
//        // GIVEN
//        Concert concert = new Concert();
//
//        // WHEN
//        Concert result = concertDao.saveConcert(concert);
//
//        // THEN
//        verify(dynamoDBMapper).save(concert);
//        assertEquals(concert, result);
//    }
//}
//
//    @Test
//    public void updateConcert_validConcertDate_callsMapperWithConcert()
//
//    @Test
//    public void updateConcert_bogusConcertDate_returnsNull() {
//        // GIVEN
//        Concert concert = new Concert();
//
//        // WHEN
//        Concert result = concertDao.saveConcert(concert);
//
//        // THEN
//        verify(dynamoDBMapper).save(concert);
//        assertEquals(concert, result);
//    }
//
//    @Test
//    public void deleteConcert_bogusConcertDate_returnsNull() {
//        // GIVEN
//        Concert concert = new Concert();
//
//        // WHEN
//        Concert result = concertDao.saveConcert(concert);
//
//        // THEN
//        verify(dynamoDBMapper).save(concert);
//        assertEquals(concert, result);
//    }
//
//    @Test
//    public void deleteConcert_vailidConcdrts_callDaoDelete() {
//        // GIVEN
//        Concert concert = new Concert();
//
//        // WHEN
//        Concert result = concertDao.saveConcert(concert);
//
//        // THEN
//        verify(dynamoDBMapper).save(concert);
//        assertEquals(concert, result);
//    }
//}
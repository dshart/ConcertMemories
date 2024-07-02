////package com.nashss.se.musicplaylistservice.activity;
////
////import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.CreateTaskRequest;
////import com.nashss.se.musicplaylistservice.activity.taskActivities.CreateTaskActivity;
////import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
////import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
////import com.nashss.se.musicplaylistservice.exceptions.TaskNotFoundException;
////import org.junit.jupiter.api.BeforeEach;
////import org.junit.jupiter.api.Test;
////import org.mockito.ArgumentCaptor;
////import org.mockito.Mock;
////
////import static org.junit.jupiter.api.Assertions.assertEquals;
////import static org.mockito.Mockito.*;
////import static org.mockito.MockitoAnnotations.openMocks;
////
////public class CreateTaskActivityTest {
////    @Mock
////    private TaskDao taskDao;
////
////    private CreateTaskActivity activity;
////
////    @BeforeEach
////    public void setUp() {
////        openMocks(this);
////        activity = new CreateTaskActivity(taskDao);
////    }
////
////    @Test
////    public void handleRequest_goodRequest_returnsNewOrganization() {
////        // GIVEN
////        String orgId = "testId";
////        String testName = "testName";
////
////
////        CreateTaskRequest request = CreateTaskRequest.builder()
////                .withOrgId(orgId)
////                .withName(testName)
////                .build();
////
////        Task task = new Task();
////        task.setOrgId(orgId);
////        task.setName(testName);
////
////        ArgumentCaptor<Task> argumentCaptor = ArgumentCaptor.forClass(Task.class);
////
////        doThrow(new TaskNotFoundException()).when(taskDao).getSingleTask(any(String.class), any(String.class));
////
////        // WHEN
////        activity.handleRequest(request);
////        verify(taskDao).writeTask(argumentCaptor.capture());
////
////        // THEN
////        assertEquals(orgId, argumentCaptor.getValue().getOrgId(), "Expected class to pass provided orgId to DAO for write");
////        assertEquals(testName, argumentCaptor.getValue().getName(), "Expected class to pass provided displayName to DAO for write");
////    }
////
////
////}//package com.nashss.se.concertmemories.api.concert.activity;
//////
//////public class CreateConcertActivityTest {
//////}
//
//
//
////package com.nashss.se.musicplaylistservice.activity;
////
////import com.nashss.se.musicplaylistservice.activity.requests.CreatePlaylistRequest;
////import com.nashss.se.musicplaylistservice.activity.results.CreatePlaylistResult;
////import com.nashss.se.musicplaylistservice.dynamodb.PlaylistDao;
////import com.nashss.se.musicplaylistservice.dynamodb.models.Playlist;
////import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
////
////import com.google.common.collect.Lists;
////import org.junit.jupiter.api.BeforeEach;
////import org.junit.jupiter.api.Test;
////import org.mockito.Mock;
////
////import java.util.List;
////import java.util.Set;
////
////import static org.junit.jupiter.api.Assertions.assertEquals;
////import static org.junit.jupiter.api.Assertions.assertNotNull;
////import static org.junit.jupiter.api.Assertions.assertNull;
////import static org.junit.jupiter.api.Assertions.assertThrows;
////import static org.mockito.ArgumentMatchers.any;
////import static org.mockito.Mockito.verify;
////import static org.mockito.MockitoAnnotations.initMocks;
////import static org.mockito.MockitoAnnotations.openMocks;
////
////public class CreateConcertActivityTest {
////    @Mock
////    private PlaylistDao playlistDao;
////
////    private CreatePlaylistActivity createPlaylistActivity;
////
////    @BeforeEach
////    void setUp() {
////        openMocks(this);
////        createPlaylistActivity = new CreatePlaylistActivity(playlistDao);
////    }
////
////    @Test
////    public void handleRequest_withTags_createsAndSavesPlaylistWithTags() {
////        // GIVEN
////        String expectedName = "expectedName";
////        String expectedCustomerId = "expectedCustomerId";
////        int expectedSongCount = 0;
////        List<String> expectedTags = List.of("tag");
////
////        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
////                .withName(expectedName)
////                .withCustomerId(expectedCustomerId)
////                .withTags(expectedTags)
////                .build();
////
////        // WHEN
////        CreatePlaylistResult result = createPlaylistActivity.handleRequest(request);
////
////        // THEN
////        verify(playlistDao).savePlaylist(any(Playlist.class));
////
////        assertNotNull(result.getPlaylist().getId());
////        assertEquals(expectedName, result.getPlaylist().getName());
////        assertEquals(expectedCustomerId, result.getPlaylist().getCustomerId());
////        assertEquals(expectedSongCount, result.getPlaylist().getSongCount());
////        assertEquals(expectedTags, result.getPlaylist().getTags());
////    }
////
////    @Test
////    public void handleRequest_noTags_createsAndSavesPlaylistWithoutTags() {
////        // GIVEN
////        String expectedName = "expectedName";
////        String expectedCustomerId = "expectedCustomerId";
////        int expectedSongCount = 0;
////
////        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
////                .withName(expectedName)
////                .withCustomerId(expectedCustomerId)
////                .build();
////
////        // WHEN
////        CreatePlaylistResult result = createPlaylistActivity.handleRequest(request);
////
////        // THEN
////        verify(playlistDao).savePlaylist(any(Playlist.class));
////
////        assertNotNull(result.getPlaylist().getId());
////        assertEquals(expectedName, result.getPlaylist().getName());
////        assertEquals(expectedCustomerId, result.getPlaylist().getCustomerId());
////        assertEquals(expectedSongCount, result.getPlaylist().getSongCount());
////        assertNull(result.getPlaylist().getTags());
////    }
////
////    @Test
////    public void handleRequest_invalidName_throwsInvalidAttributeValueException() {
////        // GIVEN
////        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
////                .withName("I'm illegal")
////                .withCustomerId("customerId")
////                .build();
////
////        // WHEN + THEN
////        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(request));
////    }
////
////    @Test
////    public void handleRequest_invalidCustomerId_throwsInvalidAttributeValueException() {
////        // GIVEN
////        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
////                .withName("AllOK")
////                .withCustomerId("Jemma's \"illegal\" customer ID")
////                .build();
////
////        // WHEN + THEN
////        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(request));
////    }
////}
//
//package com.nashss.se.concertmemories.api.concert.activity;
//import com.nashss.se.concertmemories.api.concert.activity.CreateConcertActivity;
//import com.nashss.se.concertmemories.api.concert.request.CreateConcertRequest;
//import com.nashss.se.concertmemories.api.concert.result.CreateConcertResult;
//import com.nashss.se.concertmemories.dynamodb.ConcertDao;
//import com.nashss.se.concertmemories.dynamodb.models.Concert;
//import com.nashss.se.concertmemories.exceptions.ConcertNotFoundException;
//import com.nashss.se.concertmemories.exceptions.InvalidAttributeValueException;
//
//import com.google.common.collect.Lists;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.verify;
//        import static org.mockito.MockitoAnnotations.initMocks;
//        import static org.mockito.MockitoAnnotations.openMocks;
//
//public class CreatePlaylistActivityTest {
//    @Mock
//    private ConcertDao concertDao;
//
//    private CreateConcertActivity createConcertActivity;
//
//    @BeforeEach
//    void setUp() {
//        openMocks(this);
//        createConcertActivity = new CreateConcertActivity(concertDao);
//    }
//
////    @Test
////    public void handleRequest_withTags_createsAndSavesPlaylistWithTags() {
////        // GIVEN
////        String expectedName = "expectedName";
////        String expectedCustomerId = "expectedCustomerId";
////        int expectedSongCount = 0;
////        List<String> expectedTags = List.of("tag");
////
////        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
////                .withName(expectedName)
////                .withCustomerId(expectedCustomerId)
////                .withTags(expectedTags)
////                .build();
////
////        // WHEN
////        CreatePlaylistResult result = createPlaylistActivity.handleRequest(request);
////
////        // THEN
////        verify(playlistDao).savePlaylist(any(Playlist.class));
////
////        assertNotNull(result.getPlaylist().getId());
////        assertEquals(expectedName, result.getPlaylist().getName());
////        assertEquals(expectedCustomerId, result.getPlaylist().getCustomerId());
////        assertEquals(expectedSongCount, result.getPlaylist().getSongCount());
////        assertEquals(expectedTags, result.getPlaylist().getTags());
////    }
//
////    @Test
////    public void handleRequest_noTags_createsAndSavesPlaylistWithoutTags() {
////        // GIVEN
////        String expectedName = "expectedName";
////        String expectedCustomerId = "expectedCustomerId";
////        int expectedSongCount = 0;
////
////        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
////                .withName(expectedName)
////                .withCustomerId(expectedCustomerId)
////                .build();
////
////        // WHEN
////        CreatePlaylistResult result = createPlaylistActivity.handleRequest(request);
////
////        // THEN
////        verify(playlistDao).savePlaylist(any(Playlist.class));
////
////        assertNotNull(result.getPlaylist().getId());
////        assertEquals(expectedName, result.getPlaylist().getName());
////        assertEquals(expectedCustomerId, result.getPlaylist().getCustomerId());
////        assertEquals(expectedSongCount, result.getPlaylist().getSongCount());
////        assertNull(result.getPlaylist().getTags());
////    }
////
////    @Test
////    public void handleRequest_invalidName_throwsInvalidAttributeValueException() {
////        // GIVEN
////        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
////                .withName("I'm illegal")
////                .withCustomerId("customerId")
////                .build();
////
////        // WHEN + THEN
////        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(request));
////    }
////
////    @Test
////    public void handleRequest_invalidCustomerId_throwsInvalidAttributeValueException() {
////        // GIVEN
////        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
////                .withName("AllOK")
////                .withCustomerId("Jemma's \"illegal\" customer ID")
////                .build();
////
////        // WHEN + THEN
////        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(request));
////    }
//
//        @Test
//    public void handleRequest_goodRequest_returnsNewConcert() {
//        // GIVEN
//        String emailAddress = "email";
//        String dateAttended = "dateAttended";
//
//
//        CreateConcertRequest request = CreateConcertRequest.builder()
//                .withEmailAddress(emailAddress)
//                .withDateAttended(dateAttended)
//                .build();
//
//        Concert concert = new Concert();
//        concert.setEmailAddress(emailAddress);
//        concert.setDateAttended();
//
//        ArgumentCaptor<Concert> argumentCaptor = ArgumentCaptor.forClass(Concert.class);
//
//        doThrow(new ConcertNotFoundException()).when(concertDao).getSingleTask(any(String.class), any(String.class));
//
//        // WHEN
//        activity.handleRequest(request);
//        verify(concertDao).saveConcert(argumentCaptor.capture());
//
//        // THEN
//        assertEquals(emailAddress, argumentCaptor.getValue().getEmailAddress(), "Expected class to pass provided orgId to DAO for write");
//        assertEquals(dateAttended, argumentCaptor.getValue().getDateAttended(), "Expected class to pass provided displayName to DAO for write");
//    }
////
//}

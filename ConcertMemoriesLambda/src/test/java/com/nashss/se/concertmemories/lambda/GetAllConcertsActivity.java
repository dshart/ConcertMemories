package com.nashss.se.concertmemories.api.concert.activity;

import com.nashss.se.concertmemories.api.concert.request.GetAllConcertsRequest;
import com.nashss.se.concertmemories.api.concert.result.GetAllConcertsResult;
import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import com.nashss.se.concertmemories.dynamodb.models.Concert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetAllConcertsActivityTest {
    @Mock
    private ConcertDao concertDao;

    private GetAllConcertsActivity getAllConcertsActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        getAllConcertsActivity = new GetAllConcertsActivity(concertDao);
    }

    @Test
    void handleRequest_atleastoneconcertExists_returnsconcertinlist() {
        // GIVEN

        Concert concert1 = new Concert("1988-07-16", "Def Leppard", "Hysteria");
        Concert concert2 = new Concert("1993-04-28", "Bon Jovi" "Keep The Faith");
        Concert concert3 = new Concert("2012-09-17", "Switchfoot", "Hello Hurricane");
        List<Concert> myConcertsList = new ArrayList<>();
        myConcertsList.add(concert1);
        myConcertsList.add(concert2);
        myConcertsList.add(concert3);

        GetAllConcertsRequest request = GetAllConcertsRequest.builder()
                .build();
        when(concertDao.getAllConcerts()).thenReturn(myConcertsList);


        // WHEN
        GetAllConcertsResult concertsResultList = getAllConcertActivity.handleRequest(request);

        // THEN
        //assert a list is returned
        assertEquals(List.class, concertResultList.getClass());
        //assert 3 items in list
        //same number of items
        assertEquals(myConcertsList.size(), concertsResultList.size(),
                String.format("Expected myConcerts list and concert models list to have same number of items",
        //assert list contains the 3 items in mock list
        for (int i = 0; i < myConcerts.size(); i++) {
            assertEquals(myConcertsList.get(i), concertResultList.get(i),
                  String.format("Expected %dth album track (%s) to match corresponding song model (%s)",
                                i,
                                albumTracks.get(i),
                                songModels.get(i)));
            }
        }

        //assert returned concert model same as mocked
        //void assertAlbumTrackEqualsSongModel(AlbumTrack albumTrack, SongModel songModel, String message) {
        assertEquals(albumTrack.getAsin(), songModel.getAsin(), message);
            assertEquals(albumTrack.getTrackNumber(), songModel.getTrackNumber(), message);
            assertEquals(albumTrack.getAlbumName(), songModel.getAlbum(), message);
            assertEquals(albumTrack.getSongTitle(), songModel.getTitle(), message);
        }
        AlbumTrackTestHelper.assertAlbumTracksEqualSongModels(playlist.getSongList(), result.getSongList());
    }

    @Test
    void handleRequest_playlistExistsWithoutSongs_returnsEmptyList() {
        // GIVEN
        Playlist emptyPlaylist = PlaylistTestHelper.generatePlaylistWithNAlbumTracks(0);
        String playlistId = emptyPlaylist.getId();
        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder()
                .withId(playlistId)
                .build();
        when(playlistDao.getPlaylist(playlistId)).thenReturn(emptyPlaylist);

        // WHEN
        GetPlaylistSongsResult result = getPlaylistSongsActivity.handleRequest(request);

        // THEN
        assertTrue(result.getSongList().isEmpty(),
                "Expected song list to be empty but was " + result.getSongList());
    }

    @Test
    void handleRequest_withDefaultSongOrder_returnsDefaultOrderedPlaylistSongs() {
        // GIVEN
        Playlist playlist = PlaylistTestHelper.generatePlaylistWithNAlbumTracks(10);
        String playlistId = playlist.getId();

        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder()
                .withId(playlistId)
                .withOrder(SongOrder.DEFAULT)
                .build();
        when(playlistDao.getPlaylist(playlistId)).thenReturn(playlist);

        // WHEN
        GetPlaylistSongsResult result = getPlaylistSongsActivity.handleRequest(request);

        // THEN
        AlbumTrackTestHelper.assertAlbumTracksEqualSongModels(playlist.getSongList(), result.getSongList());
    }

    @Test
    void handleRequest_withReversedSongOrder_returnsReversedPlaylistSongs() {
        // GIVEN
        Playlist playlist = PlaylistTestHelper.generatePlaylistWithNAlbumTracks(9);
        String playlistId = playlist.getId();
        List<AlbumTrack> reversedAlbumTracks = new LinkedList<>(playlist.getSongList());
        Collections.reverse(reversedAlbumTracks);

        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder()
                .withId(playlistId)
                .withOrder(SongOrder.REVERSED)
                .build();
        when(playlistDao.getPlaylist(playlistId)).thenReturn(playlist);

        // WHEN
        GetPlaylistSongsResult result = getPlaylistSongsActivity.handleRequest(request);

        // THEN
        AlbumTrackTestHelper.assertAlbumTracksEqualSongModels(reversedAlbumTracks, result.getSongList());
    }

    @Test
    void handleRequest_withShuffledSongOrder_returnsSongsInAnyOrder() {
        Playlist playlist = PlaylistTestHelper.generatePlaylistWithNAlbumTracks(8);
        String playlistId = playlist.getId();

        List<SongModel> songModels = new ModelConverter().toSongModelList(playlist.getSongList());

        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder()
                .withId(playlistId)
                .withOrder(SongOrder.REVERSED)
                .build();
        when(playlistDao.getPlaylist(playlistId)).thenReturn(playlist);

        // WHEN
        GetPlaylistSongsResult result = getPlaylistSongsActivity.handleRequest(request);

        // THEN
        assertEquals(playlist.getSongList().size(),
                result.getSongList().size(),
                String.format("Expected album tracks (%s) and song models (%s) to be the same length",
                        playlist.getSongList(),
                        result.getSongList()));
        assertTrue(
                songModels.containsAll(result.getSongList()),
                String.format("album list (%s) and song models (%s) are the same length, but don't contain the same " +
                                "entries. Expected song models: %s. Returned song models: %s",
                        playlist.getSongList(),
                        result.getSongList(),
                        songModels,
                        result.getSongList()));
    }

    @Test
    public void handleRequest_noMatchingPlaylistId_throwsPlaylistNotFoundException() {
        // GIVEN
        String id = "missingID";
        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder()
                .withId(id)
                .build();

        // WHEN
        when(playlistDao.getPlaylist(id)).thenThrow(new PlaylistNotFoundException());

        // WHEN + THEN
        assertThrows(PlaylistNotFoundException.class, () -> getPlaylistSongsActivity.handleRequest(request));
    }

    @Test
    public void handleRequest_withInvalidSongOrder_throwsException() {
        // GIVEN
        Playlist playlist = PlaylistTestHelper.generatePlaylist();
        String id = playlist.getId();
        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder()
                .withId(id)
                .withOrder("NOT A VALID ORDER")
                .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> getPlaylistSongsActivity.handleRequest(request));
    }
}
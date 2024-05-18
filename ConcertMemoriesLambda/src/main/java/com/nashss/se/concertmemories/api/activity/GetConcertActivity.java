package com.nashss.se.concertmemories.api.activity;

import com.nashss.se.concertmemories.api.request.GetConcertRequest;
import com.nashss.se.concertmemories.api.result.GetConcertResult;

import com.nashss.se.concertmemories.models.ConcertModel;

import com.nashss.se.concertmemories.converter.ModelConverter;
import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import com.nashss.se.concertmemories.dynamodb.models.Concert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetPlaylistActivity for the MusicPlaylistService's GetPlaylist API.
 *
 * This API allows the customer to get one of their saved playlists.
 */
public class GetConcertActivity {
    // private final Logger log = LogManager.getLogger();
    private final ConcertDao concertDao;

    /**
     * Instantiates a new GetPlaylistActivity object.
     *
     * @param concertDao ConcertDao to access the concert table.
     */
    @Inject
    public GetConcertActivity(ConcertDao concertDao) {
        this.concertDao = concertDao;
    }

    /**
     * This method handles the incoming request by retrieving the playlist from the database.
     * <p>
     * It then returns the playlist.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     *
     * @param getConcertRequest request object containing the concert date
     * @return getConcertResult result object containing the API defined {@link ConcertModel}
     */
    public GetConcertResult handleRequest(final GetConcertRequest getConcertRequest) {
        //log.info("Received GetPlaylistRequest {}", getPlaylistRequest);
        String dateAttended = getConcertRequest.getDateAttended();
        Concert concert = concertDao.getConcert(dateAttended);
        ConcertModel concertModel = new ModelConverter().toConcertModel(concert);

        return GetConcertResult.builder()
                .withConcert(concertModel)
                .build();
    }
}
package com.nashss.se.concertmemories.converters;

import com.nashss.se.concertmemories.models.ConcertModel;
import com.nashss.se.concertmemories.dynamodb.models.Concert;

import java.util.ArrayList;
import java.util.List;

/**
// * Converts between Data models and the representation we want to return in the result.
 */
public class ModelConverter {
    /**
     * Converts a provided {@link Concert} into a {@link ConcertModel} representation.
     *
     * @param concert the concert to convert
     * @return the converted concert
     */
    public ConcertModel toConcertModel(Concert concert) {
        List<String> openingActs = null;
        if (concert.getOpeningActs() != null) {
            openingActs = new ArrayList<>(concert.getOpeningActs());
        }

        List<String> songsPlayed = null;
        if (concert.getSongsPlayed() != null) {
            songsPlayed = new ArrayList<>(concert.getSongsPlayed());
        }

        List<String> memories = null;
        if (concert.getMemories() != null) {
            memories = new ArrayList<>(concert.getMemories());
        }

        return ConcertModel.builder()
                .withEmailAddress(concert.getEmailAddress())
                .withBandName(concert.getBandName())
                .withTourName(concert.getTourName())
                .withDateAttended(concert.getDateAttended())
                .withVenue(concert.getVenue())
                .withOpeningActs(concert.getOpeningActs())
                .withSetList(concert.getSongsPlayed())
                .withMemories(concert.getMemories())
                .build();
    }


    /**
     * Converts a list of Concerts to a list of ConcertModels.
     *
     * @param concertList The list of concerts to convert
     * @return concertModels The converted list of ConcertModels
     */
    public List<ConcertModel> toConcertModelList(List<Concert> concertList) {
        List<ConcertModel> concertModels = new ArrayList<>();
        for (Concert concert : concertList) {
            concertModels.add(toConcertModel(concert));
        }

        return concertModels;
    }
}
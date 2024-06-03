package com.nashss.se.concertmemories.api.request;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

import static com.nashss.se.concertmemories.utils.CollectionUtils.copyToList;


/**
 * Implementation of GetAllConcertRequest for ConcertMemories' GetAllConcerts API.
 *
 * This API allows the user to get all of their saved concerts.
 */
public class CreateConcertRequest {
    private final String emailAddress;
    private final String dateAttended;
    private final String bandName;
    private final String tourName;
    private final String venue;
    private final List<String> openingActs;
    private final List<String> songsPlayed;
    private final List<String> memories;

    private CreateConcertRequest(String emailAddress, String dateAttended, String bandName, String tourName, String venue,
                                 List<String> openingActs, List<String> songsPlayed, List<String> memories) {
        this.emailAddress = emailAddress;
        this.dateAttended = dateAttended;
        this.bandName = bandName;
        this.tourName = tourName;
        this.venue = venue;
        this.openingActs = openingActs;
        this.songsPlayed = songsPlayed;
        this.memories = memories;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDateAttended() {
        return dateAttended;
    }

    public String getBandName() {
        return bandName;
    }

    public String getTourName() {
        return tourName;
    }

    public String getVenue() {
        return venue;
    }

    public List<String> getOpeningActs() {
        return copyToList(openingActs);
    }

    public List<String> getSongsPlayed() {
        return copyToList(songsPlayed);
    }

    public List<String> getMemories() {
        return copyToList(memories);
    }


    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String emailAddress;
        private String dateAttended;
        private String bandName;
        private String tourName;
        private String venue;
        private List<String> openingActs;
        private List<String> songsPlayed;
        private List<String> memories;

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder withDateAttended(String dateAttended) {
            this.dateAttended = dateAttended;
            return this;
        }

        public Builder withBandName(String bandName) {
            this.bandName = bandName;
            return this;
        }

        public Builder withTourName(String tourName) {
            this.tourName = tourName;
            return this;
        }

        public Builder withVenue(String venue) {
            this.venue = venue;
            return this;
        }

        public Builder withOpeningActs(List<String> openingActs) {
            this.openingActs = copyToList(openingActs);
            return this;
        }

        public Builder withSongsPlayed(List<String> songsPlayed) {
            this.songsPlayed = copyToList(songsPlayed);
            return this;
        }


        public Builder withMemories(List<String> memories) {
            this.memories = copyToList(memories);
            return this;
        }


        public CreateConcertRequest build() {
            return new CreateConcertRequest(emailAddress, dateAttended, bandName, tourName, venue, openingActs,
                    songsPlayed, memories);
        }
    }

    @Override
    public String toString() {
        return "CreateConcertRequest{" +
                "email='" + emailAddress + '\'' +
                ", 'dateAttended='" + dateAttended + '\'' +
                ", 'bandName='" + bandName + '\'' +
                ", 'tourName='" + tourName + '\'' +
                ", 'venue='" + venue + '\'' +
                ", 'openingActs='" + openingActs + '\'' +
                ", 'songsPlayed='" + songsPlayed + '\'' +
                ", 'memories=" + memories +
                '}';

    }
}

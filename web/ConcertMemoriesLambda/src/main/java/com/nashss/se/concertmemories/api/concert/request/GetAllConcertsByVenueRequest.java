package com.nashss.se.concertmemories.api.concert.request;

/**
 * Implementation of GetAllConcertsByVenueRequest for ConcertMemories' GetAllConcertsByVenue API.
 *
 * This API allows the user to get all of their saved concerts from a specific venue.
 */
public class GetAllConcertsByVenueRequest {
    private final String emailAddress;
    private final String venue;

    private GetAllConcertsByVenueRequest(String emailAddress, String venue) {
        this.emailAddress = emailAddress;
        this.venue = venue;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getVenue() {
        return venue;
    }

    @Override
    public String toString() {
        return "GetAllConcertsByVenueRequest{" +
                "emailAddress='" + getEmailAddress() + '\'' +
                "venue='" + getVenue() + '\'' +
                '}';
    }

    /**
     * builder().
     * @return Builder
     */
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String emailAddress;
        private String venue;

        /**
         * withEmailAddress(x).
         * @param emailAddress email address of user
         * @return Builder
         */
        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        /**
         * withVenue(x).
         * @param venue name of venue user wants to access concerts for
         * @return Builder
         */
        public Builder withVenue(String venue) {
            this.venue = venue;
            return this;
        }

        /**
         * build().
         * @return GetAllConcertsByVenueRequest
         */
        public GetAllConcertsByVenueRequest build() {
            return new GetAllConcertsByVenueRequest(emailAddress, venue);
        }
    }
}
package com.nashss.se.concertmemories.api.request.concert;

public class GetConcertByVenueRequest {
    private final String emailAddress;
    private final String venue;

    private GetConcertByVenueRequest(String emailAddress, String venue) {
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
        return "GetConcertByVenueRequest{" +
                "emailAddress='" + emailAddress + '\'' +
                "venue='" + venue + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String emailAddress;
        private String venue;

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder withVenue(String venue) {
            this.venue = venue;
            return this;
        }

        public GetConcertByVenueRequest build() {
            return new GetConcertByVenueRequest(emailAddress, venue);
        }
    }
}
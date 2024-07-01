package com.nashss.se.concertmemories.api.concert.request;

/**
 * Implementation of GetAllConcertsByBandRequest for ConcertMemories' GetAllConcertsByBand API.
 *
 * This API allows the user to get all of their saved concerts of a specific band.
 */
public class GetAllConcertsByBandRequest {
    private final String emailAddress;
    private final String bandName;

    private GetAllConcertsByBandRequest(String emailAddress, String bandName) {
        this.emailAddress = emailAddress;
        this.bandName = bandName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getBandName() {
        return bandName;
    }

    @Override
    public String toString() {
        return "GetAllConcertsByBandRequest{" +
                "emailAddress='" + getEmailAddress() + '\'' +
                "bandName='" + getBandName() + '\'' +
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
        private String bandName;

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
         * withBandName(x).
         * @param bandName name of band user wants to access concerts for
         * @return Builder
         */
        public Builder withBandName(String bandName) {
            this.bandName = bandName;
            return this;
        }

        /**
         * build().
         * @return GetAllConcertsByBandRequest
         */
        public GetAllConcertsByBandRequest build() {
            return new GetAllConcertsByBandRequest(emailAddress, bandName);
        }
    }
}
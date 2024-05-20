package com.nashss.se.concertmemories.api.request.concert;

public class GetConcertByBandRequest {
    private final String emailAddress;
    private final String bandName;

    private GetConcertByBandRequest(String emailAddress, String bandName) {
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
        return "GetConcertByDateRequest{" +
                "emailAddress='" + emailAddress + '\'' +
                "bandName='" + bandName + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String emailAddress;
        private String bandName;

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder withBandName(String bandName) {
            this.bandName = bandName;
            return this;
        }

        public GetConcertByBandRequest build() {
            return new GetConcertByBandRequest(emailAddress, bandName);
        }
    }
}
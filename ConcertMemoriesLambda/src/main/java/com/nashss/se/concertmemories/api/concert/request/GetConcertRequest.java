package com.nashss.se.concertmemories.api.concert.request;

public class GetConcertRequest {
    private final String emailAddress;
    private final String dateAttended;

    private GetConcertRequest(String emailAddress, String dateAttended) {
        this.emailAddress = emailAddress;
        this.dateAttended = dateAttended;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDateAttended() {
        return dateAttended;
    }
    @Override
    public String toString() {
        return "GetConcertRequest{" +
                "emailAddress='" + emailAddress + '\'' +
                "dateAttended='" + dateAttended + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String emailAddress;
        private String dateAttended;

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder withDateAttended(String dateAttended) {
            this.dateAttended = dateAttended;
            return this;
        }

        public GetConcertRequest build() {
            return new GetConcertRequest(emailAddress, dateAttended);
        }
    }
}

package com.nashss.se.concertmemories.api.request.concert;

public class GetConcertByDateRequest {
    private final String emailAddress;
    private final String dateAttended;

    private GetConcertByDateRequest(String emailAddress, String dateAttended) {
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
        return "GetConcertByDateRequest{" +
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

        public Builder withEmailAddress(String id) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder withDateAttended(String dateAttended) {
            this.dateAttended = dateAttended;
            return this;
        }

        public GetConcertByDateRequest build() {
            return new GetConcertByDateRequest(emailAddress, dateAttended);
        }
    }
}
package com.nashss.se.concertmemories.api.concert.request;

public class DeleteConcertRequest {
    private final String emailAddress;
    private final String dateAttended;

    private DeleteConcertRequest(String emailAddress, String dateAttended) {
        this.emailAddress = emailAddress;
        this.dateAttended = dateAttended;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDateAttended() {
        return dateAttended;
    }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() { return new Builder(); }

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

        public DeleteConcertRequest build() { return new DeleteConcertRequest(emailAddress, dateAttended); }
    }
    
}
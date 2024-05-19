package com.nashss.se.concertmemories.api.request;
public class GetAllConcertsRequest {
    private final String emailAddress;

    private GetAllConcertsRequest(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "GetConcertRequest{" +
                "emailAddress='" + getEmailAddress() + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String emailAddress;

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public GetAllConcertsRequest build() {
            return new GetAllConcertsRequest(emailAddress);
        }
    }
}


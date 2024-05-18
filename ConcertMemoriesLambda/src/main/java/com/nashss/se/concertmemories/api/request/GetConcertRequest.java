package com.nashss.se.concertmemories.api.request;
public class GetConcertRequest {
    private final String dateAttended;

    private GetConcertRequest(String dateAttended) {
        this.dateAttended = dateAttended;
    }

    public String getDateAttended() {
        return dateAttended;
    }

    @Override
    public String toString() {
        return "GetConcertRequest{" +
                "dateAttended='" + getDateAttended() + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String dateAttended;

        public Builder withId(String dateAttended) {
            this.dateAttended = dateAttended;
            return this;
        }

        public GetConcertRequest build() {
            return new GetConcertRequest(dateAttended);
        }
    }
}

package com.nashss.se.concertmemories.api.result;

import com.nashss.se.concertmemories.models.ConcertModel;

public class GetConcertResult {
    private final ConcertModel concert;

    private GetConcertResult(ConcertModel concert) {
        this.concert = concert;
    }

    public ConcertModel getConcert() {
        return concert;
    }

    @Override
    public String toString() {
        return "GetConcertResult{" +
                "concert=" + concert +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ConcertModel concert;

        public Builder withConcert(ConcertModel concert) {
            this.concert = concert;
            return this;
        }

        public GetConcertResult build() {
            return new GetConcertResult(concert);
        }
    }
}
package com.nashss.se.concertmemories.api.concert.result;

import com.nashss.se.concertmemories.models.ConcertModel;

public class GetConcertResult {
    private final ConcertModel concertModel;

    private GetConcertResult(ConcertModel concertModel) {
        this.concertModel = concertModel;
    }

    public ConcertModel getConcert() {
        return concertModel;
    }

    @Override
    public String toString() {
        return "GetConcertResult{" +
                "concert=" + concertModel +
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


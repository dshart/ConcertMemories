package com.nashss.se.concertmemories.api.result.concert;

import com.nashss.se.concertmemories.models.ConcertModel;

public class GetConcertByDateResult {
    private final ConcertModel concert;

    private GetConcertByDateResult(ConcertModel concert) {
        this.concert = concert;
    }

    public ConcertModel getConcert() {
        return concert;
    }

    @Override
    public String toString() {
        return "GetConcertByDateResult{" +
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

        public GetConcertByDateResult build() {
            return new GetConcertByDateResult(concert);
        }
    }
}
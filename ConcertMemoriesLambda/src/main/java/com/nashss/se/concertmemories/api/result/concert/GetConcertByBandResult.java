package com.nashss.se.concertmemories.api.result.concert;

import com.nashss.se.concertmemories.models.ConcertModel;

public class GetConcertByBandResult {
    private final ConcertModel concert;

    private GetConcertByBandResult(ConcertModel concert) {
        this.concert = concert;
    }

    public ConcertModel getConcert() {
        return concert;
    }

    @Override
    public String toString() {
        return "GetConcertByBandResult{" +
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

        public GetConcertByBandResult build() {
            return new GetConcertByBandResult(concert);
        }
    }
}

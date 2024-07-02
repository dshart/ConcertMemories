package com.nashss.se.concertmemories.api.concert.result;

import com.nashss.se.concertmemories.models.ConcertModel;

public class UpdateConcertResult {
    private final ConcertModel concert;

    private UpdateConcertResult(ConcertModel concert) {
        this.concert = concert;
    }

    public ConcertModel getConcert() {
        return concert;
    }

    @Override
    public String toString() {
        return "UpdateConcertResult{" +
                "concert=" + concert +
        '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ConcertModel concert;

        public Builder withConcert(ConcertModel concert) {
            this.concert = concert;
            return this;
        }

        public UpdateConcertResult build() {
            return new UpdateConcertResult(concert);
        }
    }
}

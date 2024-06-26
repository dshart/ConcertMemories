package com.nashss.se.concertmemories.api.concert.result;

import com.nashss.se.concertmemories.models.ConcertModel;

public class CreateConcertResult {
    private final ConcertModel concert;

    private CreateConcertResult(ConcertModel concert) {
        this.concert = concert;
    }

    public ConcertModel getConcert() {
        return concert;
    }

    @Override
    public String toString() {
        return "CreateConcertResult{" +
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

        public CreateConcertResult build() {
            return new CreateConcertResult(concert);
        }
    }
}
package com.nashss.se.concertmemories.api.concert.result;

import com.nashss.se.concertmemories.models.ConcertModel;

public class DeleteConcertResult {

    private final ConcertModel concert;

    private DeleteConcertResult(ConcertModel concert) {
        this.concert = concert;
    }

    public ConcertModel getConcert() { return concert; }

    @Override
    public String toString() {
        return "DeleteConcertResult{" +
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
        public DeleteConcertResult build() {
            return new DeleteConcertResult(concert);
        }
    }

}

package com.nashss.se.concertmemories.api.concert.result;

import com.nashss.se.concertmemories.dynamodb.models.Concert;

public class DeleteConcertResult {

    private final Concert concert;

    private DeleteConcertResult(Concert concert) {
        this.concert = concert;
    }

    public Concert geConcert() { return concert; }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Concert concert;
        public Builder withConcert(Concert concert) {
            this.concert = concert;
            return this;
        }
        public DeleteConcertResult build() {
            return new DeleteConcertResult(concert);
        }
    }
}
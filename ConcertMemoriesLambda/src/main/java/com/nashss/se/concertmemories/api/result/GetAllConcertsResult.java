package com.nashss.se.concertmemories.api.result;

import com.nashss.se.concertmemories.models.ConcertModel;

import java.util.List;
import java.util.ArrayList;

public class GetAllConcertsResult {
    private final List<ConcertModel> concertList;

    private GetAllConcertsResult(List<ConcertModel> concertList) {
        this.concertList = concertList;
     }

    public List<ConcertModel> getAllConcerts() {
        return new ArrayList<>(concertList);
    }

    @Override
    public String toString() {
        return "GetAllConcertsResult{" +
                "concertList=" + concertList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<ConcertModel> songList;

        public Builder withSongList(List<ConcertModel> concertList) {
            this.concertList = new ArrayList<>(concertList);
            return this;
        }

        public GetAllConcertsResult build() {
            return new GetAllConcertsResult(concertList);
        }
    }
}
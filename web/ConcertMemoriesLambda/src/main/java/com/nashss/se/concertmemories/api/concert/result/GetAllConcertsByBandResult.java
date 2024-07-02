package com.nashss.se.concertmemories.api.concert.result;

import com.nashss.se.concertmemories.models.ConcertModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of GetAllConcertsByBandResult for ConcertMemories' GetAllConcertsByBand API.
 *
 * This API allows the user to get all of their saved concerts by a specific band.
 */

public class GetAllConcertsByBandResult {
    private final List<ConcertModel> concertList;

    private GetAllConcertsByBandResult(List<ConcertModel> concertList) {
        this.concertList = concertList;
    }

    public List<ConcertModel> getAllConcertsByBand() {
        return new ArrayList<>(concertList);
    }

    @Override
    public String toString() {
        return "GetAllConcertsByBandResult{" +
                "concertList=" + concertList +
                '}';
    }

    /**
     * Builder.
     * @return Builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder.
     */
    public static class Builder {
        private List<ConcertModel> concertList;


        /**
         * withConcertModelList(x).
         * @param concertList concertList
         *@return Builder
         */
        public Builder withConcertModelList(List<ConcertModel> concertList) {
            this.concertList = new ArrayList<>(concertList);
            return this;
        }
        /**
         * build().
         * @return GetAllConcertsByBandResult
         */
        public GetAllConcertsByBandResult build() {
            return new GetAllConcertsByBandResult(concertList);
        }
    }
}



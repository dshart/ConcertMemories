package com.nashss.se.concertmemories.api.concert.result;

import com.nashss.se.concertmemories.models.ConcertModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of GetAllConcertsByVenueResult for ConcertMemories' GetAllConcertsVenue API.
 *
 * This API allows the user to get all of their saved concerts from a specific venue.
 */

public class GetAllConcertsByVenueResult {
    private final List<ConcertModel> concertList;

    private GetAllConcertsByVenueResult(List<ConcertModel> concertList) {
        this.concertList = concertList;
    }

    public List<ConcertModel> getAllConcertsByVenue() {
        return new ArrayList<>(concertList);
    }

    @Override
    public String toString() {
        return "GetAllConcertsByVenueResult{" +
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
         * @return GetAllConcertsByVenueResult
         */
        public GetAllConcertsByVenueResult build() {
            return new GetAllConcertsByVenueResult(concertList);
        }
    }
}

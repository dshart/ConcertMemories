package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.request.concert.GetConcertByVenueRequest;
import com.nashss.se.concertmemories.api.result.concert.GetConcertByVenueResult;

public class GetConcertByVenueLambda
        extends LambdaActivityRunner<GetConcertByVenueRequest, GetConcertByVenueResult>
        implements RequestHandler<LambdaRequest<GetConcertByVenueRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetConcertByVenueRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetConcertByVenueRequest.builder()
                                .withEmailAddress(path.get("emailAddress"))
                                .withVenue(path.get("venue"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetConcertByVenueActivity().handleRequest(request)
        );
    }
}


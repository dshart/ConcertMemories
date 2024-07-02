package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.concert.request.GetAllConcertsByVenueRequest;
import com.nashss.se.concertmemories.api.concert.result.GetAllConcertsByVenueResult;

/**
 * GetAllConcertsByBandLambda.
 */
public class GetAllConcertsByVenueLambda
        extends LambdaActivityRunner<GetAllConcertsByVenueRequest, GetAllConcertsByVenueResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllConcertsByVenueRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllConcertsByVenueRequest> input, Context context) {
        return super.runActivity(
                () ->
                {
                    GetAllConcertsByVenueRequest stageRequest = input.fromUserClaims(claims ->
                            GetAllConcertsByVenueRequest.builder()
                                    .withEmailAddress(claims.get("email"))
                                    .build());

                    return input.fromPath(path ->
                            GetAllConcertsByVenueRequest.builder()
                                    .withEmailAddress(stageRequest.getEmailAddress())
                                    .withVenue(path.get("venue"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllConcertsByVenueActivity().handleRequest(request)
        );
    }
}
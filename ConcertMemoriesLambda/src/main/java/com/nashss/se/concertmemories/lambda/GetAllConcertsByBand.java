package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.concert.request.GetAllConcertsRequest;
import com.nashss.se.concertmemories.api.concert.request.GetConcertRequest;
import com.nashss.se.concertmemories.api.concert.result.GetAllConcertsResult;

/**
 * GetAllConcertsLambda.
 */
public class GetAllConcertsByBandLambda
        extends LambdaActivityRunner<GetAllConcertsByBandRequest, GetAllConcertsByBandResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllConcertsByBandRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllConcertsByBandRequest> input, Context context) {
        return super.runActivity(
                () ->
                {
                    GetAllConcertsRequest stageRequest = input.fromUserClaims(claims ->
                            GetAllConcertsByBandRequest.builder()
                                    .withEmailAddress(claims.get("email"))
                                    .build());

                    return input.fromPath(path ->
                            GetConcertRequest.builder()
                                    .withEmailAddress(stageRequest.getEmailAddress())
                                    .withBandName(path.get("bandName"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllConcertsActivity().handleRequest(request)
        );
    }
}


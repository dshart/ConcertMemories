package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.concert.request.GetAllConcertsByBandRequest;
import com.nashss.se.concertmemories.api.concert.result.GetAllConcertsByBandResult;

/**
 * GetAllConcertsByVenueLambda.
 */
public class GetAllConcertsByBandLambda
        extends LambdaActivityRunner<GetAllConcertsByBandRequest, GetAllConcertsByBandResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllConcertsByBandRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllConcertsByBandRequest> input, Context context) {
        return super.runActivity(
                () ->
                {
                    GetAllConcertsByBandRequest stageRequest = input.fromUserClaims(claims ->
                            GetAllConcertsByBandRequest.builder()
                                    .withEmailAddress(claims.get("email"))
                                    .build());

                    return input.fromPath(path ->
                            GetAllConcertsByBandRequest.builder()
                                    .withEmailAddress(stageRequest.getEmailAddress())
                                    .withBandName(path.get("bandName"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllConcertsByBandActivity().handleRequest(request)
        );
    }
}
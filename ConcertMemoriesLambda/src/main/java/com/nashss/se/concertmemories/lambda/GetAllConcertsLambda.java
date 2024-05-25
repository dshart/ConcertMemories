package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.concert.request.GetAllConcertsRequest;
import com.nashss.se.concertmemories.api.concert.result.GetAllConcertsResult;

/**
 * GetAllConcertsLambda.
 */
public class GetAllConcertsLambda
        extends com.nashss.se.concertmemories.lambda.LambdaActivityRunner<GetAllConcertsRequest, GetAllConcertsResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllConcertsRequest>, LambdaResponse> {

    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllConcertsRequest> input, Context context) {
        return super.runActivity(
                () ->
                {
                    GetAllConcertsRequest stageRequest = input.fromUserClaims(claims ->
                            GetAllConcertsRequest.builder()
                                    .withEmailAddress(claims.get("emailAddress"))
                                    .build());

                    return input.fromPath(path ->
                            GetAllConcertsRequest.builder()
                                    .withEmailAddress(stageRequest.getEmailAddress())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllConcertsActivity().handleRequest(request)
        );
    }
}

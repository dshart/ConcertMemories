package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.request.concert.GetConcertByDateRequest;
import com.nashss.se.concertmemories.api.result.concert.GetConcertByDateResult;

public class GetConcertByDateLambda
        extends LambdaActivityRunner<GetConcertByDateRequest, GetConcertByDateResult>
        implements RequestHandler<LambdaRequest<GetConcertByDateRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetConcertByDateRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetConcertByDateRequest.builder()
                                .withEmailAddress(path.get("emailAddress"))
                                .withDateAttended(path.get("dateAttended"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetConcertByDateActivity().handleRequest(request)
        );
    }
}

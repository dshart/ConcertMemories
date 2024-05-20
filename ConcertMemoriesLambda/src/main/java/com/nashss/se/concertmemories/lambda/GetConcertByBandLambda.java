package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.request.concert.GetConcertByBandRequest;
import com.nashss.se.concertmemories.api.result.concert.GetConcertByBandResult;

public class GetConcertByBandLambda
        extends LambdaActivityRunner<GetConcertByBandRequest, GetConcertByBandResult>
        implements RequestHandler<LambdaRequest<GetConcertByBandRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetConcertByBandRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetConcertByBandRequest.builder()
                                .withEmailAddress(path.get("emailAddress"))
                                .withBandName(path.get("dateAttended"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetConcertByBandActivity().handleRequest(request)
        );
    }
}


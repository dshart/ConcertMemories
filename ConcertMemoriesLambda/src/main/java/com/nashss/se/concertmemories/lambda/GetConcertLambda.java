package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.request.GetConcertRequest;
import com.nashss.se.concertmemories.api.result.GetConcertResult;

public class GetConcertLambda
        extends LambdaActivityRunner<GetConcertRequest, GetConcertResult>
        implements RequestHandler<LambdaRequest<GetConcertRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetConcertRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetConcertRequest.builder()
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetConcertActivity().handleRequest(request)
        );
    }
}


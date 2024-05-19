package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.request.GetAllConcertsRequest;
import com.nashss.se.concertmemories.api.result.GetAllConcertsResult;

public class GetConcertLambda
        extends LambdaActivityRunner<GetAllConcertsRequest, GetAllConcertsResult>
        implements RequestHandler<LambdaRequest<GetAllConcertsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllConcertsRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetAllConcertsRequest.builder()
                                .withUserId(path.get("emailAddress"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetConcertActivity().handleRequest(request)
        );
    }
}


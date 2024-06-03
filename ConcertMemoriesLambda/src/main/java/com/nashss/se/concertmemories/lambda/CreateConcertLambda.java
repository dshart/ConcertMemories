package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.request.CreateConcertRequest;
import com.nashss.se.concertmemories.api.result.CreateConcertResult;

public class CreateConcertLambda
        extends LambdaActivityRunner<CreateConcertRequest, CreateConcertResult>
        implements RequestHandler<LambdaRequest<CreateConcertRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateConcertRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromBody(CreateConcertRequest.class),
                (request, serviceComponent) ->
                        serviceComponent.provideCreateConcertActivity().handleRequest(request)
        );
    }
}
package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.concert.request.CreateConcertRequest;
import com.nashss.se.concertmemories.api.concert.result.CreateConcertResult;

public class CreateConcertLambda extends LambdaActivityRunner<CreateConcertRequest, CreateConcertResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateConcertRequest>, LambdaResponse> {

   @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateConcertRequest> input, Context context) {
        return super.runActivity(
                () ->
                {
                    CreateConcertRequest stageRequest = input.fromUserClaims(claims ->
                            CreateConcertRequest.builder()
                                    .withEmailAddress(claims.get("email"))
                                    .build());

                    return input.fromPath(path ->
                            CreateConcertRequest.builder()
                                    .withEmailAddress(stageRequest.getEmailAddress())
                                    .withDateAttended(path.get("dateAttended"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateConcertActivity().handleRequest(request)
        );
    }


}
package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.concert.request.GetAllConcertsRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nashss.se.concertmemories.api.concert.result.GetConcertResult;
import com.nashss.se.concertmemories.api.concert.request.GetConcertRequest;

public class GetConcertLambda extends LambdaActivityRunner<GetConcertRequest, GetConcertResult>
            implements RequestHandler<AuthenticatedLambdaRequest<GetConcertRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetConcertRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
            () ->
                {
                    GetConcertRequest stageRequest = input.fromUserClaims(claims ->
                            GetConcertRequest.builder()
                                    .withEmailAddress(claims.get("email"))
                                    .build());

                    return input.fromPath(path ->
                           GetConcertRequest.builder()
                                    .withEmailAddress(stageRequest.getEmailAddress())
                                    .withDateAttended(path.get("dateAttended"))
                                    .build());
                },

            (request, serviceComponent) ->
                serviceComponent.provideGetConcertActivity().handleRequest(request)
        );
    }
}


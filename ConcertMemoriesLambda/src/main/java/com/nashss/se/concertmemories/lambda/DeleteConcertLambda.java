package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.concert.request.DeleteConcertRequest;
import com.nashss.se.concertmemories.api.concert.request.GetConcertRequest;
import com.nashss.se.concertmemories.api.concert.result.DeleteConcertResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteConcertLambda extends LambdaActivityRunner<DeleteConcertRequest, DeleteConcertResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteConcertRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteConcertRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () ->
                {
                    DeleteConcertRequest stageRequest = input.fromUserClaims(claims ->
                            DeleteConcertRequest.builder()
                                    .withEmailAddress(claims.get("email"))
                                    .build());

                    return input.fromPath(path ->
                            DeleteConcertRequest.builder()
                                    .withEmailAddress(stageRequest.getEmailAddress())
                                    .withDateAttended(path.get("dateAttended"))
                                    .build());
                },

                (request, serviceComponent) ->
                        serviceComponent.provideDeleteConcertActivity().handleRequest(request)
        );
    }

    {
        
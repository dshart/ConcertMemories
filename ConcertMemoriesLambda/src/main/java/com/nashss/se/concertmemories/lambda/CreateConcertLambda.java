package com.nashss.se.concertmemories.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.concertmemories.api.concert.request.CreateConcertRequest;
import com.nashss.se.concertmemories.api.concert.result.CreateConcertResult;

import java.util.ArrayList;
import java.util.List;

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


                    CreateConcertRequest request = input.fromBody(CreateConcertRequest.class);
                    return input.fromUserClaims(claims ->
                        CreateConcertRequest.builder()
                        .withEmailAddress(claims.get("email"))
                        .withDateAttended(request.getDateAttended())
                        .withBandName(request.getBandName())
                        .withTourName(request.getTourName())
                        .withVenue(request.getVenue())
                        .withOpeningActs(request.getOpeningActs())
                        .withSongsPlayed(request.getSongsPlayed())
                        .withMemories(request.getMemories())
                        .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateConcertActivity().handleRequest(request)
        );
    }
}
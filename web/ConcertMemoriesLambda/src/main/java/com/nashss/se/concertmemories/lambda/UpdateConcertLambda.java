package com.nashss.se.concertmemories.lambda;

        import com.amazonaws.services.lambda.runtime.Context;
        import com.amazonaws.services.lambda.runtime.RequestHandler;
        import com.nashss.se.concertmemories.api.concert.request.CreateConcertRequest;
        import com.nashss.se.concertmemories.api.concert.request.UpdateConcertRequest;
        import com.nashss.se.concertmemories.api.concert.result.UpdateConcertResult;


public class UpdateConcertLambda extends LambdaActivityRunner<UpdateConcertRequest, UpdateConcertResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateConcertRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateConcertRequest> input, Context context) {
        return super.runActivity(
                () ->
                {
                    UpdateConcertRequest request = input.fromBody(UpdateConcertRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdateConcertRequest.builder()
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
                        serviceComponent.provideUpdateConcertActivity().handleRequest(request)
        );
    }
}
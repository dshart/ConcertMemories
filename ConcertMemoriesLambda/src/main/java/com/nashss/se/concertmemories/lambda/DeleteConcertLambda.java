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

                    String dateAttended = input.getPathParameters().get("dateAttended");
                    return input.fromUserClaims(claims ->
                        DeleteConcertRequest.builder()
                            .withDateAttended(dateAttended)
                            .withEmailAddress(claims.get("email"))
                                .build());
//                    DeleteConcertRequest stageRequest = input.fromUserClaims(claims ->
//                            DeleteConcertRequest.builder()
//                                    .withEmailAddress(claims.get("email"))
//                                    .build());
//
//                    return input.fromPath(path ->
//                            DeleteConcertRequest.builder()
//                                //    .withDateAttended("1988-07-16")
//                                //    .withEmailAddress("dshart@gmail.com")
//                                    .withDateAttended(path.get("dateAttended"))
//                                    .withEmailAddress(stageRequest.getEmailAddress())
//
//                                    .build());
                },

                (request, serviceComponent) ->
                        serviceComponent.provideDeleteConcertActivity().handleRequest(request)
        );



    }
//        log.info("handleRequest");
//        return super.runActivity(
//                () ->
//                {
//                    DeleteConcertRequest stageRequest = input.fromUserClaims(claims ->
//                            DeleteConcertRequest.builder()
//                                    .withEmailAddress(claims.get("email"))
//                                    .build());
////
////                    return input.fromPath(path ->
////                            DeleteConcertRequest.builder()
////                                    .withEmailAddress(stageRequest.getEmailAddress())
////                                    .withDateAttended(path.get("dateAttended"))
////                                    .build());
////                },
//
//                    //DeleteConcertRequest request = input.fromPath(DeleteConcertRequest.class);
//                    return input.fromPath(path ->
//                            DeleteConcertRequest.builder()
//                                    .withEmailAddress(stageRequest.getEmailAddress())
//                                    .withDateAttended(path.get("dateAttended"))
//                                    .build());
//                },
//
//                (request, serviceComponent) ->
//                        serviceComponent.provideDeleteConcertActivity().handleRequest(request)
//
//        );
//    }
}



//        CreateConcertRequest request = input.fromBody(CreateConcertRequest.class);
//        return input.fromUserClaims(claims ->
//                CreateConcertRequest.builder()
//                        .withEmailAddress(claims.get("email"))
//                        .withDateAttended(request.getDateAttended())
//                        .withBandName(request.getBandName())
//                        .withTourName(request.getTourName())
//                        .withVenue(request.getVenue())
//                        .withOpeningActs(request.getOpeningActs())
//                        .withSongsPlayed(request.getSongsPlayed())
//                        .withMemories(request.getMemories())
//                        .build());
//    },
//            (request, serviceComponent) ->
//            serviceComponent.provideCreateConcertActivity().handleRequest(request)
//        );

    //{
        
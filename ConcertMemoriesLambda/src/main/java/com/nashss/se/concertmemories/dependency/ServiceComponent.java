package com.nashss.se.concertmemories.dependency;

//import com.nashss.se.concertmemories.api.activity.artist.CreateArtistActivity;
//import com.nashss.se.concertmemories.api.activity.artist.DeleteArtistActivity;
//import com.nashss.se.concertmemories.api.activity.artist.GetArtistActivity;
//import com.nashss.se.concertmemories.api.activity.artist.UpdateArtistActivity;
import com.nashss.se.concertmemories.api.activity.*;

import com.nashss.se.concertmemories.dynamodb.ConcertDao;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Concert Memories Web App.
 */
@Singleton
@Component(modules = com.nashss.se.concertmemories.dependency.DaoModule.class)
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return ConcertDao
     */
    ConcertDao provideConcertDao();

    /**
     * Provides the relevant activity.
     * @return GetConcertActivity
     */
    GetConcertActivity provideGetConcertActivity();

    /**
     * Provides the relevant activity.
     * @return GetConcertByYearActivity
     */
    // GetConcertByYearActivity provideGetConcertByYearActivity();

    /**
     * Provides the relevant activity.
     * @return GetConcertByArtistActivity
     */
//    GetConcertByArtistActivity provideGetConcertByArtistActivity();

    /**
     * Provides the relevant activity.
     * @return GetConcertByVenueActivity
     */
    //  GetConcertByVenueActivity provideGetConcertByVenueActivity();


    /**
     * Provides the relevant activity.
     * @return UpdateConcertActivity
     */
    // UpdateConcertActivity provideUpdateConcertActivity();

    /**
     * Provides the relevant activity.
     * @return CreateConcertActivity
     */
    //CreateConcertActivity provideCreateConcertActivity();


    /**
     * Provides the relevant activity.
     * @return DeleteConcertActivity
     */
    //DeleteConcertActivity provideDeleteConcertActivity();


    /**
     * Provides the relevant activity.
     * @return GetArtistActivity
     */
    // GetArtistActivity provideGetArtistActivity();


    /**
     * Provides the relevant activity.
     * @return UpdateArtistActivity
     */
    // UpdateArtistActivity provideUpdateArtistActivity();


    /**
     * Provides the relevant activity.
     * @return CreateArtistActivity
     */
    //  CreateArtistActivity provideCreateArtistActivity();

    /**
     * Provides the relevant activity.
     * @return DeleteArtistActivity
     */
    // DeleteArtistActivity provideDeleteArtistActivity();



}

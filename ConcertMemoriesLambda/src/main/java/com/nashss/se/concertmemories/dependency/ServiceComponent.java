package com.nashss.se.concertmemories.dependency;

import com.nashss.se.concertmemories.api.concert.activity.CreateConcertActivity;
import com.nashss.se.concertmemories.api.concert.activity.GetConcertActivity;
import com.nashss.se.concertmemories.api.concert.activity.GetAllConcertsActivity;
import com.nashss.se.concertmemories.api.concert.activity.GetAllConcertsByBandActivity;
import com.nashss.se.concertmemories.api.concert.activity.GetAllConcertsByVenueActivity;
import com.nashss.se.concertmemories.api.concert.activity.DeleteConcertActivity;

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
     * @return GetAllConcertsActivity
     */
    GetAllConcertsActivity provideGetAllConcertsActivity();

    /**
     * Provides the relevant activity.
     * @return GetAllConcertsByBandActivity
     */
    GetAllConcertsByBandActivity provideGetAllConcertsByBandActivity();

    /**
     * Provides the relevant activity.
     * @return GetAllConcertsByVenueActivity
     */
     GetAllConcertsByVenueActivity provideGetAllConcertsByVenueActivity();


    /**
     * Provides the relevant activity.
     * @return UpdateConcertActivity
     */
    // UpdateConcertActivity provideUpdateConcertActivity();

    /**
     * Provides the relevant activity.
     * @return CreateConcertActivity
     */
    CreateConcertActivity provideCreateConcertActivity();


    /**
     * Provides the relevant activity.
     * @return DeleteConcertActivity
     */
    DeleteConcertActivity provideDeleteConcertActivity();








}

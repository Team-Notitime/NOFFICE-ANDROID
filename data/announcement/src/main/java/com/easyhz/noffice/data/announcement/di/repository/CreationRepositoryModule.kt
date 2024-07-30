package com.easyhz.noffice.data.announcement.di.repository

import com.easyhz.noffice.data.announcement.repository.creation.place.PlaceRepository
import com.easyhz.noffice.data.announcement.repository.creation.place.PlaceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CreationRepositoryModule {

    @Binds
    fun bindPlaceRepository(
        placeRepositoryImpl: PlaceRepositoryImpl
    ): PlaceRepository

}
package com.easyhz.noffice.data.announcement.di.repository

import com.easyhz.noffice.data.announcement.repository.announcement.AnnounceRepository
import com.easyhz.noffice.data.announcement.repository.announcement.AnnounceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AnnouncementRepositoryModule {
    @Binds
    fun bindPlaceRepository(
        announceRepositoryImpl: AnnounceRepositoryImpl
    ): AnnounceRepository
}
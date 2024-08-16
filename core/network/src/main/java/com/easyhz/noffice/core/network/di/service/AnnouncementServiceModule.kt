package com.easyhz.noffice.core.network.di.service

import com.easyhz.noffice.core.network.api.announcement.AnnouncementService
import com.easyhz.noffice.core.network.di.NofficeRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AnnouncementServiceModule {
    @Provides
    fun provideAnnouncementService(@NofficeRetrofit retrofit: Retrofit): AnnouncementService =
        retrofit.create(AnnouncementService::class.java)
}
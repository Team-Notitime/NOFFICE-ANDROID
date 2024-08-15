package com.easyhz.noffice.core.network.di.service

import com.easyhz.noffice.core.network.api.image.ImageService
import com.easyhz.noffice.core.network.di.NofficeRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ImageServiceModule {
    @Provides
    fun provideImageService(@NofficeRetrofit retrofit: Retrofit): ImageService =
        retrofit.create(ImageService::class.java)
}
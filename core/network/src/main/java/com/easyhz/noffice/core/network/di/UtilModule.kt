package com.easyhz.noffice.core.network.di

import com.easyhz.noffice.core.network.BuildConfig
import com.easyhz.noffice.core.network.adapter.ResultCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {
    @Provides
    @Singleton
    fun provideResultCallAdapterFactory(gson: Gson): ResultCallAdapterFactory =
        ResultCallAdapterFactory(gson)

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    @Provides
    @Singleton
    @Debug
    fun provideIsDebugEnabled(): Boolean = BuildConfig.DEBUG
}
package com.easyhz.noffice.core.network.di

import com.easyhz.noffice.core.network.BuildConfig
import com.easyhz.noffice.core.network.adapter.ResultCallAdapterFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @NofficeRetrofit
    @Singleton
    @Provides
    fun provideNofficeClient(
        client: OkHttpClient,
        resultCallAdapterFactory: ResultCallAdapterFactory,
        gson: Gson,
    ): Retrofit = Retrofit.Builder().apply {
        client(client)
        baseUrl(BuildConfig.NOFFICE_BASE_URL)
        addConverterFactory(GsonConverterFactory.create(gson))
        addCallAdapterFactory(resultCallAdapterFactory)
    }.build()

}
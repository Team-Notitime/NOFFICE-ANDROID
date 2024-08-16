package com.easyhz.noffice.core.network.di.service

import com.easyhz.noffice.core.network.api.organization.OrganizationService
import com.easyhz.noffice.core.network.di.NofficeRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object OrganizationServiceModule {
    @Provides
    fun provideOrganizationService(@NofficeRetrofit retrofit: Retrofit): OrganizationService =
        retrofit.create(OrganizationService::class.java)
}
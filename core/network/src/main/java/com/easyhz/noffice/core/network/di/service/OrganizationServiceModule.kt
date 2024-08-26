package com.easyhz.noffice.core.network.di.service

import com.easyhz.noffice.core.network.api.organization.OrganizationMemberService
import com.easyhz.noffice.core.network.api.organization.OrganizationService
import com.easyhz.noffice.core.network.api.promotion.PromotionService
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

    @Provides
    fun provideOrganizationMemberService(@NofficeRetrofit retrofit: Retrofit): OrganizationMemberService =
        retrofit.create(OrganizationMemberService::class.java)

    @Provides
    fun providePromotionService(@NofficeRetrofit retrofit: Retrofit): PromotionService =
        retrofit.create(PromotionService::class.java)
}
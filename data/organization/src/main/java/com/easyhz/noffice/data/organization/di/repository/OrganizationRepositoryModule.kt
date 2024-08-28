package com.easyhz.noffice.data.organization.di.repository

import com.easyhz.noffice.data.organization.repository.deepLink.DeepLinkRepository
import com.easyhz.noffice.data.organization.repository.deepLink.DeepLinkRepositoryImpl
import com.easyhz.noffice.data.organization.repository.member.OrganizationMemberRepository
import com.easyhz.noffice.data.organization.repository.member.OrganizationMemberRepositoryImpl
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepository
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepositoryImpl
import com.easyhz.noffice.data.organization.repository.promotion.PromotionRepository
import com.easyhz.noffice.data.organization.repository.promotion.PromotionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface OrganizationRepositoryModule {
    @Binds
    fun bindOrganizationRepository(
        organizationRepositoryImpl: OrganizationRepositoryImpl
    ): OrganizationRepository

    @Binds
    fun bindOrganizationMemberRepository(
        organizationMemberRepositoryImpl: OrganizationMemberRepositoryImpl
    ): OrganizationMemberRepository

    @Binds
    fun bindPromotionRepository(
        promotionRepositoryImpl: PromotionRepositoryImpl
    ): PromotionRepository

    @Binds
    fun bindDeepLinkRepository(
        deepLinkRepositoryImpl: DeepLinkRepositoryImpl
    ): DeepLinkRepository
}
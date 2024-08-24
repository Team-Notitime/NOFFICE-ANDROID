package com.easyhz.noffice.data.organization.di.repository

import com.easyhz.noffice.data.organization.repository.member.OrganizationMemberRepository
import com.easyhz.noffice.data.organization.repository.member.OrganizationMemberRepositoryImpl
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepository
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepositoryImpl
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
}
package com.easyhz.noffice.data.notification.di

import com.easyhz.noffice.data.notification.repository.messaging.CloudMessagingRepository
import com.easyhz.noffice.data.notification.repository.messaging.CloudMessagingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCloudMessagingRepository(
        cloudMessagingRepositoryImpl: CloudMessagingRepositoryImpl
    ): CloudMessagingRepository
}
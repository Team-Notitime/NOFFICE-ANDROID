package com.easyhz.noffice.data.notification.di

import com.google.firebase.messaging.FirebaseMessaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CloudMessagingModule {

    @Provides
    fun provideMessagingInstance(): FirebaseMessaging = FirebaseMessaging.getInstance()
}
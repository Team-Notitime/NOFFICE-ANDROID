package com.easyhz.noffice.data.organization.di.deepLink


import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DeepLinkModule {

    @Provides
    fun provideDeepLink() = Firebase.dynamicLinks
}
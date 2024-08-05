package com.easyhz.noffice.data.organization.di.repository

import com.easyhz.noffice.data.organization.repository.image.ImageRepository
import com.easyhz.noffice.data.organization.repository.image.ImageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ImageRepositoryModule {
    @Binds
    fun bindImageRepository(
        imageRepositoryImpl: ImageRepositoryImpl
    ): ImageRepository
}
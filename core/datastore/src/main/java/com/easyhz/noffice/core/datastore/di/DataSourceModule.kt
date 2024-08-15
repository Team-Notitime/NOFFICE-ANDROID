package com.easyhz.noffice.core.datastore.di

import com.easyhz.noffice.core.datastore.datasource.auth.AuthLocalDataSource
import com.easyhz.noffice.core.datastore.datasource.auth.AuthLocalDataSourceImpl
import com.easyhz.noffice.core.datastore.datasource.user.UserLocalDataSource
import com.easyhz.noffice.core.datastore.datasource.user.UserLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindAuthLocalDataSource(
        authLocalDataSourceImpl: AuthLocalDataSourceImpl
    ): AuthLocalDataSource

    @Binds
    fun bindUserLocalDataSource(
        userLocalDataSourceImpl: UserLocalDataSourceImpl
    ): UserLocalDataSource
}
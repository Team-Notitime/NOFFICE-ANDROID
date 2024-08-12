package com.easyhz.noffice.data.member.di.repository

import com.easyhz.noffice.data.member.repository.user.UserRepository
import com.easyhz.noffice.data.member.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserRepositoryModule {
    @Binds
    fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}
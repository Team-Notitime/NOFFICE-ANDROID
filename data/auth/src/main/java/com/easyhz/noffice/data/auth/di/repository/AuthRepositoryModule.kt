package com.easyhz.noffice.data.auth.di.repository

import com.easyhz.noffice.data.auth.repository.login.LoginRepository
import com.easyhz.noffice.data.auth.repository.login.LoginRepositoryIml
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthRepositoryModule {

    @Binds
    fun bindAuthRepository(
        loginRepositoryIml: LoginRepositoryIml
    ): LoginRepository
}
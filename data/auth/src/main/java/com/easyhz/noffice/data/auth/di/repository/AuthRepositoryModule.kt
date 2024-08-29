package com.easyhz.noffice.data.auth.di.repository

import com.easyhz.noffice.data.auth.repository.auth.AuthRepository
import com.easyhz.noffice.data.auth.repository.auth.AuthRepositoryIml
import com.easyhz.noffice.data.auth.repository.token.TokenRepository
import com.easyhz.noffice.data.auth.repository.token.TokenRepositoryIml
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthRepositoryModule {

    @Binds
    fun bindAuthRepository(
        authRepositoryIml: AuthRepositoryIml
    ): AuthRepository

    @Binds
    fun bindTokenRepository(
        tokenRepositoryIml: TokenRepositoryIml
    ): TokenRepository
}
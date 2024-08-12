package com.easyhz.noffice.data.auth.di.repository

import com.easyhz.noffice.data.auth.repository.login.LoginRepository
import com.easyhz.noffice.data.auth.repository.login.LoginRepositoryIml
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
    fun bindLoginRepository(
        loginRepositoryIml: LoginRepositoryIml
    ): LoginRepository

    @Binds
    fun bindTokenRepository(
        tokenRepositoryIml: TokenRepositoryIml
    ): TokenRepository
}
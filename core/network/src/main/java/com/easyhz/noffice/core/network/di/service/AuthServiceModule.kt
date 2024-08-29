package com.easyhz.noffice.core.network.di.service

import com.easyhz.noffice.core.network.api.auth.AuthService
import com.easyhz.noffice.core.network.api.auth.TokenService
import com.easyhz.noffice.core.network.api.member.MemberService
import com.easyhz.noffice.core.network.di.NofficeRetrofit
import com.easyhz.noffice.core.network.di.TokenRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AuthServiceModule {
    @Provides
    fun provideAuthService(@NofficeRetrofit retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    fun provideTokeService(@TokenRetrofit retrofit: Retrofit): TokenService =
        retrofit.create(TokenService::class.java)

    @Provides
    fun provideMemberService(@NofficeRetrofit retrofit: Retrofit): MemberService =
        retrofit.create(MemberService::class.java)
}
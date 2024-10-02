package com.easyhz.noffice.data.auth.di.strategy

import com.easyhz.noffice.data.auth.strategy.BaseStrategy
import com.easyhz.noffice.data.auth.strategy.GoogleStrategy
import com.easyhz.noffice.data.auth.strategy.KakaoStrategy
import com.easyhz.noffice.data.auth.util.google
import com.easyhz.noffice.data.auth.util.kakao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
@InstallIn(SingletonComponent::class)
object LoginStrategyModule {

    @Provides
    @IntoMap
    @StringKey(google)
    fun provideGoogleStrategy(googleStrategy: GoogleStrategy): BaseStrategy = googleStrategy

    @Provides
    @IntoMap
    @StringKey(kakao)
    fun provideKakaoStrategy(kakaoStrategy: KakaoStrategy): BaseStrategy = kakaoStrategy
}
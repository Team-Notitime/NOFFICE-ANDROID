package com.easyhz.noffice.data.auth.di.strategy

import com.easyhz.noffice.data.auth.strategy.BaseStrategy
import com.easyhz.noffice.data.auth.strategy.GoogleStrategy
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginStrategyModule {

    @Binds
    @Singleton
    abstract fun bindGoogleStrategy(
        googleStrategy: GoogleStrategy
    ): BaseStrategy
}
package com.easyhz.noffice.core.common.di

import com.easyhz.noffice.core.common.di.NofficeDispatchers.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @Dispatcher(IO)
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
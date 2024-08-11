package com.easyhz.noffice.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import com.easyhz.noffice.core.datastore.util.authDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataStoreModule {
    companion object {

        @Singleton
        @Provides
        fun provideUserDataStorePreferences(
            @ApplicationContext context: Context
        ): DataStore<Preferences> =
            context.authDataStore
    }
}
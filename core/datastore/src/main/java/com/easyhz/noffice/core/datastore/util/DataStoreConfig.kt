package com.easyhz.noffice.core.datastore.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "noffice-auth"
)

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "noffice-user"
)
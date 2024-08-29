package com.easyhz.noffice.core.datastore.datasource.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers.IO
import com.easyhz.noffice.core.datastore.di.AuthDataStore
import com.easyhz.noffice.core.datastore.util.AuthKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher,
    @AuthDataStore private val dataStore: DataStore<Preferences>
): AuthLocalDataSource {
    private val accessToken = stringPreferencesKey(AuthKey.ACCESS_TOKEN.key)
    private val refreshToken = stringPreferencesKey(AuthKey.REFRESH_TOKEN.key)
    private val authProvider = stringPreferencesKey(AuthKey.AUTH_PROVIDER.key)

    override suspend fun getAccessToken(): Result<String> = withContext(dispatcher) {
        val preferences = dataStore.data.first()
        return@withContext preferences[accessToken]?.let {
            Result.success(it)
        } ?: Result.failure(generateNullException(AuthKey.ACCESS_TOKEN))
    }

    override suspend fun getRefreshToken(): Result<String> = withContext(dispatcher) {
        val preferences = dataStore.data.first()
        return@withContext preferences[refreshToken]?.let {
            Result.success(it)
        } ?: Result.failure(generateNullException(AuthKey.REFRESH_TOKEN))
    }

    override suspend fun deleteToken(): Unit = withContext(dispatcher) {
        dataStore.edit { preferences ->
            preferences.remove(accessToken)
            preferences.remove(refreshToken)
        }
    }

    override suspend fun updateAccessToken(access: String): Unit = withContext(dispatcher) {
        dataStore.edit { preferences ->
            preferences[accessToken] = access
        }
    }

    override suspend fun updateTokens(access: String, refresh: String): Unit = withContext(dispatcher) {
        dataStore.edit { preferences ->
            preferences[accessToken] = access
            preferences[refreshToken] = refresh
        }
    }

    override suspend fun getAuthProvider(): Result<String> = withContext(dispatcher) {
        val preferences = dataStore.data.first()
        return@withContext preferences[authProvider]?.let {
            Result.success(it)
        } ?: Result.failure(generateNullException(AuthKey.AUTH_PROVIDER))
    }

    override suspend fun updateAuthProvider(provider: String): Unit = withContext(dispatcher) {
        dataStore.edit { preferences ->
            preferences[authProvider] = provider
        }
    }

    override suspend fun deleteAuthProvider() {
        dataStore.edit { preferences ->
            preferences.remove(authProvider)
        }
    }

    private fun generateNullException(authKey: AuthKey): Exception {
        return Exception("${authKey.key} is null")
    }
}
package com.easyhz.noffice.core.datastore.datasource.user

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers
import com.easyhz.noffice.core.datastore.di.UserDataStore
import com.easyhz.noffice.core.datastore.util.UserKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    @Dispatcher(NofficeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    @UserDataStore private val dataStore: DataStore<Preferences>
) : UserLocalDataSource {
    private val isFirstRun = booleanPreferencesKey(UserKey.IS_FIRST_RUN.key)
    private val memberId = intPreferencesKey(UserKey.MEMBER_ID.key)
    private val memberName = stringPreferencesKey(UserKey.MEMBER_NAME.key)

    override suspend fun getFirstRun(): Result<Boolean> = withContext(dispatcher) {
        runCatching {
            val preferences = dataStore.data.first()
            return@runCatching preferences[isFirstRun]
                ?: throw generateNullException(UserKey.IS_FIRST_RUN)
        }
    }

    override suspend fun updateFirstRun(newValue: Boolean): Unit = withContext(dispatcher) {
        dataStore.edit { preferences ->
            preferences[isFirstRun] = newValue
        }
    }

    override suspend fun getMemberId(): Result<Int> = withContext(dispatcher) {
        runCatching {
            val preferences = dataStore.data.first()
            return@runCatching preferences[memberId]
                ?: throw generateNullException(UserKey.MEMBER_ID)
        }
    }

    override suspend fun updateMemberId(newValue: Int): Unit = withContext(dispatcher) {
        dataStore.edit { preferences ->
            preferences[memberId] = newValue
        }
    }

    override suspend fun deleteMemberId() {
        dataStore.edit { preferences ->
            preferences.remove(memberId)
        }
    }

    override suspend fun getMemberName(): Result<String>  = withContext(dispatcher) {
        runCatching {
            val preferences = dataStore.data.first()
            return@runCatching preferences[memberName]
                ?: throw generateNullException(UserKey.MEMBER_NAME)
        }
    }


    override suspend fun updateMemberName(newValue: String): Unit = withContext(dispatcher) {
        dataStore.edit { preferences ->
            preferences[memberName] = newValue
        }
    }

    override suspend fun deleteMemberName() {
        dataStore.edit { preferences ->
            preferences.remove(memberName)
        }
    }

    private fun generateNullException(userKey: UserKey): Exception {
        return Exception("${userKey.key} is null")
    }
}
package com.easyhz.noffice.data.auth.repository.token

import com.easyhz.noffice.core.datastore.datasource.auth.AuthLocalDataSource
import javax.inject.Inject

class TokenRepositoryIml @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource
): TokenRepository {
    override suspend fun getAccessToken(): Result<String> {
        return authLocalDataSource.getAccessToken()
    }
}
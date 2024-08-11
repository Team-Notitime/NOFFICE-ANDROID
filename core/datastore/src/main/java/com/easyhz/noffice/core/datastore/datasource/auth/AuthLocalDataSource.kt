package com.easyhz.noffice.core.datastore.datasource.auth


interface AuthLocalDataSource {
    suspend fun getAccessToken(): Result<String>
    suspend fun getRefreshToken(): Result<String>
    suspend fun deleteToken()
    suspend fun updateAccessToken(access: String)
    suspend fun updateTokens(access: String, refresh: String)
}
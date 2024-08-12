package com.easyhz.noffice.data.auth.repository.token

interface TokenRepository {
    suspend fun getAccessToken(): Result<String>
}
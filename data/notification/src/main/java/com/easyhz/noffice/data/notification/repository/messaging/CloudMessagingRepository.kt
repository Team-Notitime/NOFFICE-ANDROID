package com.easyhz.noffice.data.notification.repository.messaging

interface CloudMessagingRepository {
    suspend fun getToken(): Result<String>
    suspend fun registerToken(token: String): Result<Unit>
}
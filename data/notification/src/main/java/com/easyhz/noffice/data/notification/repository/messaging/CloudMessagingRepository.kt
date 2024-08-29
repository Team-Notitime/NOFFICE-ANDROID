package com.easyhz.noffice.data.notification.repository.messaging

interface CloudMessagingRepository {
    suspend fun getToken(): Result<String>
    suspend fun registerMessagingToken(token: String): Result<Unit>
}
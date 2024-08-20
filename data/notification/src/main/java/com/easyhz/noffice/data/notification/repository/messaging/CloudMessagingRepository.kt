package com.easyhz.noffice.data.notification.repository.messaging

interface CloudMessagingRepository {
    suspend fun getToken(): Result<String>
}
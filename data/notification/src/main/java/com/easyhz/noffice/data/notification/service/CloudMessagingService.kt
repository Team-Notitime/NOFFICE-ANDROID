package com.easyhz.noffice.data.notification.service

import android.util.Log
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.core.datastore.datasource.user.UserLocalDataSource
import com.easyhz.noffice.core.network.api.auth.AuthService
import com.easyhz.noffice.core.network.model.request.token.MessagingToken
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CloudMessagingService: FirebaseMessagingService() {
    @Inject
    lateinit var authService: AuthService

    @Inject
    lateinit var userLocalDataSource: UserLocalDataSource

    @Inject
    lateinit var notificationService: NotificationService

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM SERVICE ", "Refreshed token: $token")
        registerToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("FCM SERVICE ", "message data: ${message.data}")
        notificationService.showNotification(message)
    }

    private fun registerToken(token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val memberId = userLocalDataSource.getMemberId().getOrNull()
            if (memberId == null || memberId == -1) {
                Log.w(this.javaClass.name, "memberId is $memberId")
                return@launch
            }
            val response = authService.registerMessagingToken(MessagingToken(token))
            response.onSuccess {
                Log.d(this.javaClass.name, "Success registering token")
            }.onFailure { e ->
                errorLogging(this.javaClass.name, "Error registering token", e)
            }
        }
    }
}
package com.easyhz.noffice.data.notification.service

import android.util.Log
import com.easyhz.noffice.core.network.api.auth.AuthService
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
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM SERVICE ", "Refreshed token: $token")
        registerToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("FCM SERVICE ", "message data: ${message.data}")
        val service = NotificationService(applicationContext)
        service.showNotification(message)
    }

    private fun registerToken(token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = authService.registerMessagingToken(token)
            response.onSuccess {
                Log.d(this.javaClass.name, "Success registering token")
            }.onFailure { e ->
                Log.e(this.javaClass.name, "Error registering token", e)
            }
        }
    }

}
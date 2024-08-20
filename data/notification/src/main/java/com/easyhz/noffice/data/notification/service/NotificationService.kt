package com.easyhz.noffice.data.notification.service

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.easyhz.noffice.data.notification.R
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationService
@Inject constructor(
    @ApplicationContext private val context: Context
){
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(message: RemoteMessage) {
        val title = message.notification?.title
        val body = message.notification?.body
        // TODO INTENT 추가

        val notification = NotificationCompat.Builder(context, context.getString(CHANNEL_ID))
            .setSmallIcon(R.drawable.app_icon)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(0, notification)
    }

    companion object {
        val CHANNEL_ID = R.string.notification_channel_id
        val CHANNEL_NAME = R.string.notification_channel_name
    }
}
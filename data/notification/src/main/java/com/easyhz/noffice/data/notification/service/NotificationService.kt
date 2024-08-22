package com.easyhz.noffice.data.notification.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.easyhz.noffice.data.notification.R
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationService
@Inject constructor(
    @ApplicationContext private val context: Context,
){
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(message: RemoteMessage) {
        val title = message.data["title"]
        val body = message.data["body"]
        val announcementId = message.data["announcementId"]?.toIntOrNull()
        val organizationId = message.data["organizationId"]?.toIntOrNull()
        val announcementTitle = message.data["announcementTitle"]

        val pendingIntent = setIntent(announcementId = announcementId, organizationId = organizationId, announcementTitle = announcementTitle)
        val notification = NotificationCompat.Builder(context, context.getString(CHANNEL_ID))
            .setSmallIcon(R.drawable.ic_splash_logo)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(0, notification)
    }

    private fun setIntent(announcementId: Int?, organizationId: Int?, announcementTitle: String?): PendingIntent? {
        if (announcementId == null || organizationId == null) return PendingIntent.getActivity(context, 0, Intent(), PendingIntent.FLAG_IMMUTABLE)
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = "noffice://announcement?id=${announcementId}&organizationId=${organizationId}&title=${announcementTitle}".toUri()
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    companion object {
        val CHANNEL_ID = R.string.notification_channel_id
        val CHANNEL_NAME = R.string.notification_channel_name
    }
}
package com.easyhz.noffice

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.easyhz.noffice.data.notification.service.NotificationService
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NofficeApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        initKakao()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                this.getString(NotificationService.CHANNEL_ID),
                this.getString(NotificationService.CHANNEL_NAME),
                NotificationManager.IMPORTANCE_HIGH,
            )
            channel.description = getString(com.easyhz.noffice.data.notification.R.string.notification_channel_description)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun initKakao() {
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}
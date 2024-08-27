package com.easyhz.noffice.feature.notification.contract

import com.easyhz.noffice.core.common.base.UiIntent

sealed class NotificationIntent: UiIntent() {
    data object ClickBackButton: NotificationIntent()
    data class ClickNotification(val announcementId: Int, val organizationId: Int): NotificationIntent()
}
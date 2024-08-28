package com.easyhz.noffice.feature.notification.contract

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.notification.Notification

data class NotificationState(
    val notificationList: List<Notification>
): UiState() {
    companion object {
        fun init() = NotificationState(
            notificationList = emptyList()
        )
    }
}
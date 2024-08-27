package com.easyhz.noffice.feature.notification.contract

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class NotificationSideEffect: UiSideEffect() {
    data object NavigateToUp: NotificationSideEffect()
    data class NavigateToAnnouncementDetail(val organizationId: Int, val announcementId: Int,): NotificationSideEffect()
}
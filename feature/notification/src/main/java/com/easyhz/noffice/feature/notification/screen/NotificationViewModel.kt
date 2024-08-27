package com.easyhz.noffice.feature.notification.screen

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.notification.contract.NotificationIntent
import com.easyhz.noffice.feature.notification.contract.NotificationSideEffect
import com.easyhz.noffice.feature.notification.contract.NotificationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(

): BaseViewModel<NotificationState, NotificationIntent, NotificationSideEffect>(
    initialState = NotificationState.init()
)  {

    override fun handleIntent(intent: NotificationIntent) {
        when(intent) {
            is NotificationIntent.ClickBackButton -> { navigateToUp() }
            is NotificationIntent.ClickNotification -> {
                onClickNotification(intent.announcementId, intent.organizationId)
            }
        }
    }

    private fun onClickNotification(organizationId: Int, announcementId: Int) {
        postSideEffect { NotificationSideEffect.NavigateToAnnouncementDetail(organizationId, announcementId) }
    }

    private fun navigateToUp() {
        postSideEffect { NotificationSideEffect.NavigateToUp }
    }
}
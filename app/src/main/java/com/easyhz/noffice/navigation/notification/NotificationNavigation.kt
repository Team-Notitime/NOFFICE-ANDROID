package com.easyhz.noffice.navigation.notification

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.easyhz.noffice.feature.notification.screen.NotificationScreen
import com.easyhz.noffice.navigation.notification.screen.Notification

internal fun NavGraphBuilder.notificationGraph(
    navigateToUp: () -> Unit,
    navigateToAnnouncementDetail: (organizationId: Int, announcementId: Int) -> Unit,
) {
    composable<Notification> {
        NotificationScreen(
            navigateToUp = navigateToUp,
            navigateToAnnouncementDetail = navigateToAnnouncementDetail
        )
    }
}

internal fun NavController.navigateToNotification() {
    navigate(route = Notification)
}
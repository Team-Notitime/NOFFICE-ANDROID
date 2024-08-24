package com.easyhz.noffice.core.common.deepLink

import androidx.navigation.navDeepLink

object NofficeDeepLink {
    val announcement = navDeepLink {
        uriPattern = DeepLinkPatterns.announcement("{announcementId}", "{organizationId}")
    }
    val join = navDeepLink {
        uriPattern = DeepLinkPatterns.join("{organizationId}")
    }
}

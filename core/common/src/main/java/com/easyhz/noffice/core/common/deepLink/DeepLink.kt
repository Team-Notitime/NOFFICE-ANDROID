package com.easyhz.noffice.core.common.deepLink

import androidx.navigation.navDeepLink
import com.easyhz.noffice.core.common.util.Encryption

object NofficeDeepLink {
    val announcement = navDeepLink {
        uriPattern = DeepLinkPatterns.announcement("{announcementId}", "{organizationId}")
    }
    val join = navDeepLink {
        uriPattern = DeepLinkPatterns.join("{organizationId}")
    }
}
fun Int.toNofficeDeepLink(): String {
    val id = Encryption.encrypt(this.toString())
    return DeepLinkPatterns.join(id)
}
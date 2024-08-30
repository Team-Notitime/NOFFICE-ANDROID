package com.easyhz.noffice.core.common.deepLink

object DeepLinkPatterns {
    private const val ANNOUNCEMENT = "noffice://announcement?announcementId={announcementId}&organizationId={organizationId}"
    private const val JOIN = "noffice://join?organizationId={organizationId}"

    fun join(id: String?) = JOIN.replace("{organizationId}", id ?: "null")

    fun announcement(announcementId: String, organizationId: String) = ANNOUNCEMENT
        .replace("{announcementId}", announcementId)
        .replace("{organizationId}", organizationId)
}
package com.easyhz.noffice.navigation.announcement.screen

import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementSuccess(
    val organizationId: Int,
    val announcementId: Int,
    val title: String
)

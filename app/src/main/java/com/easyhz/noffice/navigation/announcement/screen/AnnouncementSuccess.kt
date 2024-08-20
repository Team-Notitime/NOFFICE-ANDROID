package com.easyhz.noffice.navigation.announcement.screen

import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementSuccess(
    val announcementId: Int,
    val title: String
)

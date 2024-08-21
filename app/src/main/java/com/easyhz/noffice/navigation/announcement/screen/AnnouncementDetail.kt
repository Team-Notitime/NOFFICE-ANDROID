package com.easyhz.noffice.navigation.announcement.screen

import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementDetail(
    val organizationId: Int,
    val id: Int,
    val title: String,
)
package com.easyhz.noffice.navigation.home.screen

import kotlinx.serialization.Serializable

@Serializable
data class Splash(
    val announcementId: Int = -1,
    val organizationId: Int = -1
)
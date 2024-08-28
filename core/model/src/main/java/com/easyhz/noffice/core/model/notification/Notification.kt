package com.easyhz.noffice.core.model.notification

data class Notification(
    val id: Int,
    val announcementId: Int,
    val organizationId: Int,
    val title: String,
    val content: String,
    val date: String
)

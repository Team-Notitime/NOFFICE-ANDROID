package com.easyhz.noffice.core.model.organization.announcement

data class OrganizationAnnouncement(
    val announcementId: Int,
    val title: String,
    val content: String,
    val place: String?,
    val placeUrl: String?,
    val taskSize: Int?,
    val endAt: String?,
    val createdAt: String,
    val updatedAt: String,
)
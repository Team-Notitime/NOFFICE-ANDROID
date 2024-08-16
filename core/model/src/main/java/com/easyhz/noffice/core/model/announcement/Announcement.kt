package com.easyhz.noffice.core.model.announcement

data class Announcement(
    val announcementId: Int,
    val content: String,
    val createdAt: String,
    val endAt: String?,
    val memberId: Int,
    val noticeAt: List<String>,
    val organizationId: Int,
    val placeLinkName: String,
    val placeLinkUrl: String,
    val profileImageUrl: String,
    val title: String,
    val updatedAt: String
)

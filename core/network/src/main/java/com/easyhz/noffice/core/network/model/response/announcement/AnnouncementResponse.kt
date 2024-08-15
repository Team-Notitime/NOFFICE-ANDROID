package com.easyhz.noffice.core.network.model.response.announcement

data class AnnouncementResponse(
    val announcements: List<AnnouncementItem>
)
data class AnnouncementItem(
    val announcementId: Int,
    val memberId: Int,
    val organizationId: Int,
    val title: String,
    val content: String,
    val noticeAt: List<String>,
    val placeLinkName: String,
    val placeLinkUrl: String,
    val profileImageUrl: String,
    val createdAt: String,
    val endAt: String?,
    val updatedAt: String
)
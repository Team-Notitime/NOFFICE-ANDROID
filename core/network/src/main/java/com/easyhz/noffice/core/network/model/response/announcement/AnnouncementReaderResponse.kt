package com.easyhz.noffice.core.network.model.response.announcement

data class AnnouncementReaderResponse(
    val announcementId: Int,
    val memberList: List<MemberResponse>
)
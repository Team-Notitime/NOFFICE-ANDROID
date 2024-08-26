package com.easyhz.noffice.core.network.model.response.announcement

import com.easyhz.noffice.core.network.model.response.member.MemberResponse

data class AnnouncementReaderResponse(
    val announcementId: Int,
    val memberList: List<MemberResponse>
)
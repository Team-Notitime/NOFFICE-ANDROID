package com.easyhz.noffice.data.announcement.mapper.announcement

import com.easyhz.noffice.core.model.announcement.detail.AnnouncementReader
import com.easyhz.noffice.core.model.common.Member
import com.easyhz.noffice.core.network.model.response.announcement.AnnouncementReaderResponse
import com.easyhz.noffice.core.network.model.response.announcement.MemberResponse

internal fun AnnouncementReaderResponse.toModel(): AnnouncementReader = AnnouncementReader(
    announcementId = this.announcementId,
    memberList = this.memberList.map { it.toModel() }
)

internal fun MemberResponse.toModel(): Member = Member(
    id = this.id,
    name = this.name,
    alias = this.alias,
    profileImage = this.profileImage
)
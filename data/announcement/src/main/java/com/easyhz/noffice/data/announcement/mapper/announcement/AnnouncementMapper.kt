package com.easyhz.noffice.data.announcement.mapper.announcement

import com.easyhz.noffice.core.common.util.DateFormat
import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.network.model.response.announcement.AnnouncementResponse

internal fun AnnouncementResponse.toModel(): List<Announcement> = this.announcements.map {
    Announcement(
        announcementId = it.announcementId,
        content = it.content,
        createdAt = it.createdAt,
        endAt = DateFormat.formatDateTime(it.endAt),
        memberId = it.memberId,
        noticeAt = it.noticeAt,
        organizationId = it.organizationId,
        placeLinkName = it.placeLinkName,
        placeLinkUrl = it.placeLinkUrl,
        profileImageUrl = it.profileImageUrl,
        title = it.title,
        updatedAt = it.updatedAt
    )
}

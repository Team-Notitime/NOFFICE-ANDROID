package com.easyhz.noffice.data.announcement.mapper.announcement

import com.easyhz.noffice.core.common.util.DateFormat
import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.network.model.response.announcement.AnnouncementItem

internal fun AnnouncementItem.toModel(): Announcement = Announcement(
    announcementId = this.announcementId,
    content = this.content,
    createdAt = this.createdAt,
    endAt = DateFormat.formatDateTime(this.endAt),
    memberId = this.memberId,
    noticeAt = this.noticeAt,
    organizationId = this.organizationId,
    placeLinkName = this.placeLinkName,
    placeLinkUrl = this.placeLinkUrl,
    profileImageUrl = this.profileImageUrl,
    title = this.title,
    updatedAt = this.updatedAt
)
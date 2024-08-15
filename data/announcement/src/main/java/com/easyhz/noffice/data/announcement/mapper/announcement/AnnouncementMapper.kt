package com.easyhz.noffice.data.announcement.mapper.announcement

import com.easyhz.noffice.core.common.util.DateFormat
import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.model.organization.announcement.OrganizationAnnouncement
import com.easyhz.noffice.core.network.model.response.announcement.AnnouncementItem

fun AnnouncementItem.toModel(): Announcement = Announcement(
    announcementId = this.announcementId,
    content = this.content,
    createdAt = this.createdAt,
    endAt = DateFormat.formatDateTimeNullable(this.endAt),
    memberId = this.memberId,
    noticeAt = this.noticeAt,
    organizationId = this.organizationId,
    placeLinkName = this.placeLinkName,
    placeLinkUrl = this.placeLinkUrl,
    profileImageUrl = this.profileImageUrl,
    title = this.title,
    updatedAt = this.updatedAt
)

fun AnnouncementItem.toDetail(): OrganizationAnnouncement = OrganizationAnnouncement(
    announcementId = this.announcementId,
    title = this.title,
    content = this.content,
    place = this.placeLinkName,
    placeUrl = this.placeLinkUrl,
    taskSize = 0,  // FIXME
    createdAt = DateFormat.formatDateTime(this.createdAt),
    endAt = DateFormat.formatDateTimeNullable(this.endAt, pattern = DateFormat.PATTERN.DAY),
    updatedAt = DateFormat.formatDateTime(this.createdAt)
)
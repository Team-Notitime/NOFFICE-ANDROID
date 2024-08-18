package com.easyhz.noffice.data.announcement.mapper.announcement

import com.easyhz.noffice.core.common.util.DateFormat
import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam
import com.easyhz.noffice.core.model.organization.announcement.OrganizationAnnouncement
import com.easyhz.noffice.core.network.model.request.announcement.AnnouncementRequest
import com.easyhz.noffice.core.network.model.request.announcement.Task
import com.easyhz.noffice.core.network.model.response.announcement.AnnouncementResponse

fun AnnouncementResponse.toModel(): Announcement = Announcement(
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

fun AnnouncementParam.toRequest(): AnnouncementRequest = AnnouncementRequest(
    organizationId = this.organizationId,
    memberId = this.memberId,
    title = this.title,
    content = this.content,
    placeLinkName = this.placeLinkName,
    placeLinkUrl = this.placeLinkUrl,
    profileImageUrl = this.profileImageUrl,
    isFaceToFace = this.isFaceToFace,
    endAt = this.endAt,
    noticeBefore = this.noticeBefore,
    noticeDate = this.noticeDate,
    tasks = this.tasks.map { Task(content = it.content) }
)

fun AnnouncementResponse.toDetail(): OrganizationAnnouncement = OrganizationAnnouncement(
    announcementId = this.announcementId,
    title = this.title,
    content = this.content,
    profileImage = this.profileImageUrl,
    place = this.placeLinkName,
    placeUrl = this.placeLinkUrl,
    taskSize = 0,  // FIXME
    createdAt = DateFormat.formatDateTime(this.createdAt),
    endAt = DateFormat.formatDateTimeNullable(this.endAt, pattern = DateFormat.PATTERN.DAY),
    updatedAt = DateFormat.formatDateTime(this.createdAt)
)
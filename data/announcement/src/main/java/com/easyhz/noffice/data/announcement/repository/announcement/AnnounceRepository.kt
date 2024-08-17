package com.easyhz.noffice.data.announcement.repository.announcement

import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam

interface AnnounceRepository {
    suspend fun createAnnouncement(param: AnnouncementParam): Result<Announcement>
    suspend fun fetchAnnouncement(announcementId: Int): Result<Announcement>
}
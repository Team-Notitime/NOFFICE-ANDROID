package com.easyhz.noffice.data.announcement.repository.announcement

import com.easyhz.noffice.core.model.announcement.Announcement

interface AnnounceRepository {
    suspend fun fetchAllAnnouncements(): Result<List<Announcement>>
    suspend fun fetchAnnouncement(announcementId: Int): Result<Announcement>
}
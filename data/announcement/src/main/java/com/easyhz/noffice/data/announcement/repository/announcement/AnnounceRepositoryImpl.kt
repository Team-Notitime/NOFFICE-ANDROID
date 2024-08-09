package com.easyhz.noffice.data.announcement.repository.announcement

import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.network.api.announcement.AnnouncementService
import com.easyhz.noffice.core.network.util.toResult
import com.easyhz.noffice.data.announcement.mapper.announcement.toModel
import javax.inject.Inject

class AnnounceRepositoryImpl @Inject constructor(
    private val announceService: AnnouncementService
) : AnnounceRepository {
    override suspend fun fetchAllAnnouncements(): Result<List<Announcement>> =
        announceService.fetchAllAnnouncements().toResult()
            .map { it.announcements.map { item -> item.toModel() } }

    override suspend fun fetchAnnouncement(announcementId: Int): Result<Announcement> =
        announceService.fetchAnnouncement(announcementId).toResult().map { it.toModel() }
}
package com.easyhz.noffice.data.announcement.repository.announcement

import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam
import com.easyhz.noffice.core.network.api.announcement.AnnouncementService
import com.easyhz.noffice.core.network.util.toResult
import com.easyhz.noffice.data.announcement.mapper.announcement.toModel
import com.easyhz.noffice.data.announcement.mapper.announcement.toRequest
import javax.inject.Inject

class AnnounceRepositoryImpl @Inject constructor(
    private val announceService: AnnouncementService
) : AnnounceRepository {
    override suspend fun createAnnouncement(param: AnnouncementParam): Result<Announcement> =
        announceService.createAnnouncement(request = param.toRequest()).toResult()
            .map { it.toModel() }


    override suspend fun fetchAnnouncement(announcementId: Int): Result<Announcement> =
        announceService.fetchAnnouncement(announcementId).toResult().map { it.toModel() }
}
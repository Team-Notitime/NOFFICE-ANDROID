package com.easyhz.noffice.domain.announcement.usecase.announcement

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.data.announcement.repository.announcement.AnnounceRepository
import javax.inject.Inject

class FetchAnnouncementUseCase @Inject constructor(
    private val announceRepository: AnnounceRepository
): BaseUseCase<Int, Announcement>() {
    override suspend fun invoke(param: Int): Result<Announcement> =
        announceRepository.fetchAnnouncement(param)
}
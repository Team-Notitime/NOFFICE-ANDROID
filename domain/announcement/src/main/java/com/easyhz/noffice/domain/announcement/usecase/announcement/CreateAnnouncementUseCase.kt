package com.easyhz.noffice.domain.announcement.usecase.announcement

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam
import com.easyhz.noffice.data.announcement.repository.announcement.AnnounceRepository
import javax.inject.Inject

class CreateAnnouncementUseCase @Inject constructor(
    private val announceRepository: AnnounceRepository
): BaseUseCase<AnnouncementParam, Announcement>() {
    override suspend fun invoke(param: AnnouncementParam): Result<Announcement> =
        announceRepository.createAnnouncement(param)
}
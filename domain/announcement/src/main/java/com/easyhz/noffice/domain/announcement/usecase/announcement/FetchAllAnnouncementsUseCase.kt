package com.easyhz.noffice.domain.announcement.usecase.announcement

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.data.announcement.repository.announcement.AnnounceRepository
import javax.inject.Inject

class FetchAllAnnouncementsUseCase @Inject constructor(
    private val announceRepository: AnnounceRepository
): BaseUseCase<Unit, List<Announcement>>() {
    override suspend fun invoke(param: Unit): Result<List<Announcement>> =
        announceRepository.fetchAllAnnouncements()
}
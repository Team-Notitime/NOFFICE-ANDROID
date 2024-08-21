package com.easyhz.noffice.domain.announcement.usecase.reader

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.announcement.detail.AnnouncementReader
import com.easyhz.noffice.data.announcement.repository.announcement.AnnounceRepository
import javax.inject.Inject

class FetchAnnouncementNonReadersUseCase @Inject constructor(
    private val announceRepository: AnnounceRepository,
): BaseUseCase<Int, AnnouncementReader>() {
    override suspend fun invoke(param: Int): Result<AnnouncementReader> {
        return announceRepository.fetchAnnouncementNonReaders(param)
    }
}
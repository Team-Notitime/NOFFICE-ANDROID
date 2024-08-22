package com.easyhz.noffice.domain.announcement.usecase.task

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.task.Task
import com.easyhz.noffice.data.announcement.repository.announcement.AnnounceRepository
import javax.inject.Inject

class FetchAnnouncementTaskUseCase @Inject constructor(
    private val announcementRepository: AnnounceRepository
): BaseUseCase<Int, List<Task>>() {
    override suspend fun invoke(param: Int): Result<List<Task>> {
        return announcementRepository.fetchAnnouncementTask(param)
    }
}
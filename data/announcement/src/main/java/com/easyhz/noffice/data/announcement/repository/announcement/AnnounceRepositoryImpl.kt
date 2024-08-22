package com.easyhz.noffice.data.announcement.repository.announcement

import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers
import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.model.announcement.detail.AnnouncementReader
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam
import com.easyhz.noffice.core.model.task.Task
import com.easyhz.noffice.core.network.api.announcement.AnnouncementService
import com.easyhz.noffice.core.network.util.toResult
import com.easyhz.noffice.data.announcement.mapper.announcement.toModel
import com.easyhz.noffice.data.announcement.mapper.announcement.toRequest
import com.easyhz.noffice.data.announcement.mapper.task.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnnounceRepositoryImpl @Inject constructor(
    @Dispatcher(NofficeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val announceService: AnnouncementService
) : AnnounceRepository {
    override suspend fun createAnnouncement(param: AnnouncementParam): Result<Announcement> = withContext(dispatcher) {
        return@withContext announceService.createAnnouncement(request = param.toRequest()).toResult()
            .map { it.toModel() }
    }


    override suspend fun fetchAnnouncement(announcementId: Int): Result<Announcement> = withContext(dispatcher) {
        return@withContext announceService.fetchAnnouncement(announcementId).toResult().map { it.toModel() }
    }

    override suspend fun fetchAnnouncementTask(announcementId: Int): Result<List<Task>> = withContext(dispatcher) {
        return@withContext announceService.fetchAnnouncementTask(announcementId).toResult()
            .map { it.tasks.map { item -> item.toModel() } }
    }

    override suspend fun fetchAnnouncementReaders(announcementId: Int): Result<AnnouncementReader> = withContext(dispatcher) {
        return@withContext announceService.fetchAnnouncementReaders(announcementId).toResult().map { it.toModel() }
    }

    override suspend fun fetchAnnouncementNonReaders(announcementId: Int): Result<AnnouncementReader> = withContext(dispatcher) {
        return@withContext announceService.fetchAnnouncementNonReaders(announcementId).toResult().map { it.toModel() }
    }
}
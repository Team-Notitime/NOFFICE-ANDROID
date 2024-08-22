package com.easyhz.noffice.data.announcement.repository.announcement

import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.model.announcement.detail.AnnouncementReader
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam
import com.easyhz.noffice.core.model.task.Task

interface AnnounceRepository {
    suspend fun createAnnouncement(param: AnnouncementParam): Result<Announcement>
    suspend fun fetchAnnouncement(announcementId: Int): Result<Announcement>
    suspend fun fetchAnnouncementTask(announcementId: Int): Result<List<Task>>
    suspend fun fetchAnnouncementReaders(announcementId: Int): Result<AnnouncementReader>
    suspend fun fetchAnnouncementNonReaders(announcementId: Int): Result<AnnouncementReader>
}
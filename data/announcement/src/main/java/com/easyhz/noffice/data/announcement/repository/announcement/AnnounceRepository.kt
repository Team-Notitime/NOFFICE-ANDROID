package com.easyhz.noffice.data.announcement.repository.announcement

import com.easyhz.noffice.core.model.announcement.Announcement

interface AnnounceRepository {
    suspend fun fetchAllAnnouncement(): Result<List<Announcement>>
}
package com.easyhz.noffice.core.network.api.announcement

import com.easyhz.noffice.core.network.model.response.announcement.AnnouncementResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.GET

interface AnnouncementService {
    @GET("/api/v1/announcement")
    suspend fun fetchAllAnnouncements(): NofficeResult<AnnouncementResponse>
}
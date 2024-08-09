package com.easyhz.noffice.core.network.api.announcement

import com.easyhz.noffice.core.network.model.response.announcement.AnnouncementItem
import com.easyhz.noffice.core.network.model.response.announcement.AnnouncementResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.GET
import retrofit2.http.Path

interface AnnouncementService {
    @GET("/api/v1/announcement")
    suspend fun fetchAllAnnouncements(): NofficeResult<AnnouncementResponse>

    @GET("/api/v1/announcement/{announcementId}")
    suspend fun fetchAnnouncement(
        @Path("announcementId") announcementId: Int
    ): NofficeResult<AnnouncementItem>
}
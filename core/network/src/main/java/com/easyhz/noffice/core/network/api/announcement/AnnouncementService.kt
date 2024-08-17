package com.easyhz.noffice.core.network.api.announcement

import com.easyhz.noffice.core.network.model.request.announcement.AnnouncementRequest
import com.easyhz.noffice.core.network.model.response.announcement.AnnouncementResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AnnouncementService {
    /* 노티 생성 */
    @POST("/api/v1/announcement")
    suspend fun createAnnouncement(
        @Body request: AnnouncementRequest
    ): NofficeResult<AnnouncementResponse>

    @GET("/api/v1/announcement/{announcementId}")
    suspend fun fetchAnnouncement(
        @Path("announcementId") announcementId: Int
    ): NofficeResult<AnnouncementResponse>
}
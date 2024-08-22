package com.easyhz.noffice.core.network.api.announcement

import com.easyhz.noffice.core.network.model.request.announcement.AnnouncementRequest
import com.easyhz.noffice.core.network.model.response.announcement.AnnouncementReaderResponse
import com.easyhz.noffice.core.network.model.response.announcement.AnnouncementResponse
import com.easyhz.noffice.core.network.model.response.announcement.AnnouncementTaskResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AnnouncementService {
    /* 노티 생성 */
    @POST("/api/v1/announcement")
    suspend fun createAnnouncement(
        @Body request: AnnouncementRequest
    ): NofficeResult<AnnouncementResponse>

    /* 조직에 발급된 노티 열람*/
    @GET("/api/v1/announcement/{announcementId}")
    suspend fun fetchAnnouncement(
        @Path("announcementId") announcementId: Int
    ): NofficeResult<AnnouncementResponse>

    /* 노티 수정 */
    @POST("/api/v1/announcement/{announcementId}")
    suspend fun updateAnnouncement(
        @Path("announcementId") announcementId: Int,
        @Body request: AnnouncementRequest
    ): NofficeResult<AnnouncementResponse>

    /* 노티 삭제 */
    @DELETE("/api/v1/announcement/{announcementId}")
    suspend fun deleteAnnouncement(
        @Path("announcementId") announcementId: Int,
    ): NofficeResult<Unit>

    /* 노티에 발급된 투두 조회 */
    @GET("/api/v1/announcement/{announcementId}/tasks")
    suspend fun fetchAnnouncementTask(
        @Path("announcementId") announcementId: Int,
    ): NofficeResult<AnnouncementTaskResponse>

    /* 공지 열람 사용자 조회 */
    @GET("/api/v1/announcement/{announcementId}/readers")
    suspend fun fetchAnnouncementReaders(
        @Path("announcementId") announcementId: Int,
    ): NofficeResult<AnnouncementReaderResponse>

    /* 공지 미열람 사용자 조회 */
    @GET("/api/v1/announcement/{announcementId}/unreaders")
    suspend fun fetchAnnouncementNonReaders(
        @Path("announcementId") announcementId: Int,
    ): NofficeResult<AnnouncementReaderResponse>
}
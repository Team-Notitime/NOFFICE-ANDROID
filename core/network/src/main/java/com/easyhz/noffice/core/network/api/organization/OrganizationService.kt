package com.easyhz.noffice.core.network.api.organization

import com.easyhz.noffice.core.network.model.request.organization.OrganizationCreationRequest
import com.easyhz.noffice.core.network.model.response.announcement.AnnouncementItem
import com.easyhz.noffice.core.network.model.response.organization.OrganizationJoinResponse
import com.easyhz.noffice.core.network.model.response.organization.OrganizationResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import com.easyhz.noffice.core.network.util.PagingResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OrganizationService {

    @GET("/api/v1/organizations")
    suspend fun fetchOrganizations(
        @Query("memberId") memberId: Int,
        @Query("page") page: Int,
        @Query("size") size: Int = 10,
        @Query("sort") sort: List<String>
    ): PagingResult<OrganizationResponse>

    @POST("/api/v1/organizations")
    suspend fun createOrganization(
        @Query("memberId") memberId: Int,
        @Body body: OrganizationCreationRequest
    ): NofficeResult<OrganizationResponse>

    @GET("/api/v1/organizations/{organizationId}")
    suspend fun fetchOrganizationInfo(
        @Path("organizationId") organizationId: Int
    ): NofficeResult<OrganizationResponse>

    @GET("/api/v1/organizations/{organizationId}/announcements")
    suspend fun fetchAnnouncementsByOrganization(
        @Path("organizationId") organizationId: Int,
        @Query("memberId") memberId: Int,
        @Query("page") page: Int,
        @Query("size") size: Int = 10,
        @Query("sort") sort: List<String>
    ): PagingResult<AnnouncementItem>

    @POST("/api/v1/organizations/{organizationId}/join")
    suspend fun joinOrganization(
        @Path("organizationId") organizationId: Int,
        @Query("memberId") memberId: Int,
    ): NofficeResult<OrganizationJoinResponse>
}
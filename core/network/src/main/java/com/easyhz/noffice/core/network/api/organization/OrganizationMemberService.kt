package com.easyhz.noffice.core.network.api.organization

import com.easyhz.noffice.core.network.model.request.organization.RegisterMemberRequest
import com.easyhz.noffice.core.network.model.response.announcement.MemberResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface OrganizationMemberService {
    /* 조직 가입 대기자 조회 */
    @GET("/api/v1/organizations/{organizationId}/pending-members")
    suspend fun fetchOrganizationPendingMembers(
        @Path("organizationId") organizationId: Int
    ): NofficeResult<List<MemberResponse>>

    /* 대기 멤버 수락 */
    @PATCH("/api/v1/organizations/{organizationId}/register")
    suspend fun acceptRegisterMember(
        @Path("organizationId") organizationId: Int,
        @Body body: RegisterMemberRequest
    ): NofficeResult<Unit>
}
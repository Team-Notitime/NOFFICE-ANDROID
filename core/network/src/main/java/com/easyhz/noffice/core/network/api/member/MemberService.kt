package com.easyhz.noffice.core.network.api.member

import com.easyhz.noffice.core.network.model.request.member.AliasRequest
import com.easyhz.noffice.core.network.model.response.auth.UserInfoResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface MemberService {
    /* 단일 회원 정보 조회 */
    @GET("/api/v1/member")
    suspend fun fetchUserInfo(): NofficeResult<UserInfoResponse>


    /* 회원 별명 변경 */
    @PATCH("/api/v1/member/alias")
    suspend fun updateUserAlias(
        @Body request: AliasRequest
    ): NofficeResult<Unit>
}
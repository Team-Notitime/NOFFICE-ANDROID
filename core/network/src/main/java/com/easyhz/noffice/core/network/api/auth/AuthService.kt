package com.easyhz.noffice.core.network.api.auth

import com.easyhz.noffice.core.network.model.request.sign.LoginRequest
import com.easyhz.noffice.core.network.model.response.auth.UserInfoResponse
import com.easyhz.noffice.core.network.model.response.auth.UserResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    /* 로그인 */
    @POST("/api/v1/member/login")
    suspend fun login(
        @Body body: LoginRequest
    ): NofficeResult<UserResponse>

    /* 단일 회원 정보 조회 */
    @GET("/api/v1/member")
    suspend fun fetchUserInfo(): NofficeResult<UserInfoResponse>
}
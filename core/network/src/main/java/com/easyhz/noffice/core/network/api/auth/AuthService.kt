package com.easyhz.noffice.core.network.api.auth

import com.easyhz.noffice.core.network.model.request.sign.LoginRequest
import com.easyhz.noffice.core.network.model.response.auth.UserResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    /* 로그인 */
    @POST("/api/v1/member/login")
    suspend fun login(
        @Body body: LoginRequest
    ): NofficeResult<UserResponse>


    /* fcm 토큰 저장 */
    @POST("/api/v1/notifications/fcm-token")
    suspend fun registerMessagingToken(
        @Body fcmToken: String
    ): NofficeResult<Unit>
}
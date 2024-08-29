package com.easyhz.noffice.core.network.api.auth

import com.easyhz.noffice.core.network.model.request.sign.LoginRequest
import com.easyhz.noffice.core.network.model.response.auth.UserResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    /* 로그인 */
    @POST("/api/v1/member/login")
    suspend fun login(
        @Body body: LoginRequest
    ): NofficeResult<UserResponse>

    /* 로그 아웃 */
    @POST("/api/v1/member/logout")
    suspend fun logout(
        @Header("notification-token") notificationToken: String
    ): NofficeResult<Unit>

    /* fcm 토큰 저장 */
    @POST("/api/v1/notifications/fcm-token")
    suspend fun registerMessagingToken(
        @Body fcmToken: String
    ): NofficeResult<Unit>

    /* 회원 탈퇴 */
    @DELETE("/api/v1/member/withdrawal")
    suspend fun withdrawal(): NofficeResult<Unit>
}
package com.easyhz.noffice.core.network.api.auth

import com.easyhz.noffice.core.network.model.response.auth.TokenResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.Header
import retrofit2.http.POST

interface TokenService {

    /* 엑세스 토큰 재발급 */
    @POST("/api/v1/member/reissue")
    suspend fun reissueAccessToken(
        @Header("Authorization") refreshToken: String
    ): NofficeResult<TokenResponse>
}
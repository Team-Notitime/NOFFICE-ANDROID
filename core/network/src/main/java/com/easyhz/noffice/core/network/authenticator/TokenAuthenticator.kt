package com.easyhz.noffice.core.network.authenticator

import com.easyhz.noffice.core.datastore.datasource.auth.AuthLocalDataSource
import com.easyhz.noffice.core.network.api.auth.TokenService
import com.easyhz.noffice.core.network.model.response.auth.TokenResponse
import com.easyhz.noffice.core.network.util.toResult
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val tokenService: TokenService
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code != 401) return null
        val refreshToken = fetchRefreshToken() ?: return null
        val token = reissueRefreshToken(refreshToken).getOrNull() ?: return null
        val newRequest = response.request.newBuilder().apply {
            removeHeader("Authorization")
            addHeader("Authorization", "Bearer ${token.accessToken}")
        }.build()

        if (token.refreshToken == refreshToken) {
            updateLocalAccessToken(refreshToken)
        } else {
            updateLocalTokens(token)
        }
        return newRequest
    }

    private fun fetchRefreshToken(): String? = runBlocking {
        return@runBlocking authLocalDataSource.getRefreshToken().getOrNull()
    }

    private fun reissueRefreshToken(refreshToken: String): Result<TokenResponse> = runBlocking {
        return@runBlocking tokenService.reissueAccessToken(refreshToken).toResult()
    }

    private fun updateLocalAccessToken(accessToken: String) = runBlocking {
        return@runBlocking authLocalDataSource.updateAccessToken(accessToken)
    }

    private fun updateLocalTokens(token: TokenResponse) = runBlocking {
        return@runBlocking authLocalDataSource.updateTokens(access = token.accessToken, refresh = token.refreshToken)
    }
}
package com.easyhz.noffice.data.auth.strategy

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class KakaoStrategy @Inject constructor() : BaseStrategy() {
    private val tag = this.javaClass.name
    override suspend fun login(context: Context): Result<String> = runCatching {
        val token = loginWithKakaoTalk(context)
        Log.d(tag, "카카오톡으로 로그인 성공 ${token.accessToken}")
        token.accessToken
    }.recoverCatching { error ->
        Log.d(tag, "카카오톡으로 로그인 실패 : $error")

        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) throw error // 의도적인 로그인 취소

        val token = loginWithKakaoAccount(context)
        Log.d(tag, "카카오 계정으로 로그인 성공 ${token.accessToken}")
        token.accessToken
    }

    override suspend fun logout(context: Context): Result<Unit> {
        TODO("Not yet implemented")
    }

    /**
     * 카카오톡으로 로그인
     *
     * @param context Context
     * @return [OAuthToken] 로그인 성공 시 발급되는 토큰
     */
    private suspend fun loginWithKakaoTalk(context: Context): OAuthToken = suspendCancellableCoroutine { continuation ->
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            when {
                error != null -> continuation.resumeWithException(error)
                token != null -> continuation.resume(token)
                else -> continuation.resumeWithException(IllegalStateException("Unexpected error during KakaoTalk login"))
            }
        }
    }

    /**
     * 카카오 계정으로 로그인
     *
     * * 카카오톡에 연결된 카카오 계정이 없을 경우 카카오 계정 로그인 시도
     *
     * @param context Context
     * @return [OAuthToken] 로그인 성공 시 발급되는 토큰
     */
    private suspend fun loginWithKakaoAccount(context: Context): OAuthToken = suspendCancellableCoroutine { continuation ->
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            when {
                error != null -> continuation.resumeWithException(error)
                token != null -> continuation.resume(token)
                else -> continuation.resumeWithException(IllegalStateException("Unexpected error during KakaoAccount login"))
            }
        }
    }
}
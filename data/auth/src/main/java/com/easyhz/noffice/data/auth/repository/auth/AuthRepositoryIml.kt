package com.easyhz.noffice.data.auth.repository.auth

import android.content.Context
import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers.IO
import com.easyhz.noffice.core.datastore.datasource.auth.AuthLocalDataSource
import com.easyhz.noffice.core.datastore.datasource.user.UserLocalDataSource
import com.easyhz.noffice.core.network.api.auth.AuthService
import com.easyhz.noffice.core.network.model.response.auth.UserResponse
import com.easyhz.noffice.core.network.util.toResult
import com.easyhz.noffice.data.auth.strategy.AuthStrategyContext
import com.easyhz.noffice.data.auth.util.Provider
import com.easyhz.noffice.data.notification.repository.messaging.CloudMessagingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryIml @Inject constructor(
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher,
    private val authStrategyContext: AuthStrategyContext,
    private val authService: AuthService,
    private val authLocalDataSource: AuthLocalDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val cloudMessagingRepository: CloudMessagingRepository
) : AuthRepository {
    override suspend fun login(context: Context, provider: String): Result<Unit> = withContext(dispatcher) {
        runCatching {
            authStrategyContext.setStrategy(provider)
            val authCode = authStrategyContext.login(context).getOrThrow()
            val request = Provider.valueOf(provider).getLoginRequest(authCode)
            val user = authService.login(request).toResult().getOrThrow()
            saveLocalUserInfo(user)
            Unit
        }
    }

    override suspend fun logout(context: Context): Result<Unit> = withContext(dispatcher) {
        runCatching {
            logoutFromServer()
            localLogout(context)
            Unit
        }
    }

    override suspend fun withdraw(context: Context): Result<Unit> = withContext(dispatcher) {
        runCatching {
            authService.withdrawal().toResult()
            localLogout(context)
            Unit
        }
    }

    private suspend fun saveLocalUserInfo(user: UserResponse) = coroutineScope {
        val authJob = async {
            val token = user.token
            authLocalDataSource.updateTokens(token.accessToken, token.refreshToken)
            authLocalDataSource.updateAuthProvider(user.provider)
        }
        val userJob = async {
            userLocalDataSource.updateMemberId(user.memberId)
        }
        awaitAll(authJob, userJob)
    }

    /**
     *  로컬에서 로그아웃을 처리합니다.
     */
    private suspend fun localLogout(context: Context) = coroutineScope {
        val provider = getAuthProvider().getOrThrow()
        authStrategyContext.setStrategy(provider)
        val logoutJob = async { authStrategyContext.logout(context) }
        val deleteJob = async { deleteLocalUserInfo() }
        awaitAll(logoutJob, deleteJob)
    }

    /**
     *  로컬에 저장된 유저 정보를 삭제합니다.
     */
    private suspend fun deleteLocalUserInfo() = coroutineScope {
        val authJob = async {
            authLocalDataSource.deleteToken()
            authLocalDataSource.deleteAuthProvider()
        }
        val userJob = async {
            userLocalDataSource.deleteMemberId()
        }
        awaitAll(authJob, userJob)
    }

    /**
     * 로컬에 저장된 인증 방식을 가져옵니다.
     */
    private suspend fun getAuthProvider(): Result<String> {
        return authLocalDataSource.getAuthProvider()
    }

    /**
     * 서버에서 로그아웃을 처리합니다.
     */
    private suspend fun logoutFromServer() {
        val token = cloudMessagingRepository.getToken().getOrThrow()
        authService.logout(token).toResult()
    }
}
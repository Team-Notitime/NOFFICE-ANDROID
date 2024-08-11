package com.easyhz.noffice.data.auth.repository.login

import android.content.Context
import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers.IO
import com.easyhz.noffice.core.datastore.datasource.auth.AuthLocalDataSource
import com.easyhz.noffice.core.network.api.auth.AuthService
import com.easyhz.noffice.core.network.util.toResult
import com.easyhz.noffice.data.auth.strategy.AuthStrategyContext
import com.easyhz.noffice.data.auth.util.Provider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryIml @Inject constructor(
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher,
    private val authStrategyContext: AuthStrategyContext,
    private val authService: AuthService,
    private val authLocalDataSource: AuthLocalDataSource,
) : LoginRepository {
    override suspend fun login(context: Context, provider: String): Result<Unit> = withContext(dispatcher) {
        runCatching {
            authStrategyContext.setStrategy(provider)
            val authCode = authStrategyContext.login(context).getOrThrow()
            val request = Provider.valueOf(provider).getLoginRequest(authCode)
            val user = authService.login(request).toResult().getOrThrow()
            val token = user.token
            authLocalDataSource.updateTokens(token.accessToken, refresh = token.refreshToken)
            authLocalDataSource.updateAuthProvider(user.provider)
        }
    }
}
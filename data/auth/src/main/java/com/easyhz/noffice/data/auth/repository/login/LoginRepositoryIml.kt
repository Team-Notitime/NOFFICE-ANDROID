package com.easyhz.noffice.data.auth.repository.login

import android.content.Context
import com.easyhz.noffice.core.network.api.auth.AuthService
import com.easyhz.noffice.core.network.model.request.sign.LoginRequest
import com.easyhz.noffice.data.auth.strategy.GoogleStrategy
import com.easyhz.noffice.data.auth.util.Provider
import javax.inject.Inject

class LoginRepositoryIml @Inject constructor(
    private val googleStrategy: GoogleStrategy,
    private val authService: AuthService
): LoginRepository {
    override suspend fun loginWithGoogle(context: Context): Result<Unit> = runCatching {
        val authorizationCode = googleStrategy.login(context).getOrThrow()
        val request = LoginRequest(
            code = authorizationCode,
            provider = Provider.GOOGLE.name
        )

        val a = authService.login(request).getOrThrow()
    }
}
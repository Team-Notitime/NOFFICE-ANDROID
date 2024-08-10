package com.easyhz.noffice.domain.sign.usecase

import android.content.Context
import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.data.auth.repository.login.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
): BaseUseCase<Context, Unit>() {
    override suspend fun invoke(param: Context): Result<Unit> =
        loginRepository.loginWithGoogle(param)
}
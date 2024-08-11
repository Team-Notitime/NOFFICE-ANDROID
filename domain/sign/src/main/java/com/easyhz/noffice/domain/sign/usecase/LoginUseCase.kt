package com.easyhz.noffice.domain.sign.usecase

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.auth.param.AuthParam
import com.easyhz.noffice.data.auth.repository.login.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
): BaseUseCase<AuthParam , Unit>() {
    override suspend fun invoke(param: AuthParam): Result<Unit> =
        loginRepository.login(param.context, param.providerName)
}
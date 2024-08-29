package com.easyhz.noffice.domain.sign.usecase

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.auth.param.AuthParam
import com.easyhz.noffice.data.auth.repository.auth.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
): BaseUseCase<AuthParam, Unit>() {
    override suspend fun invoke(param: AuthParam): Result<Unit> =
        authRepository.login(param.context, param.providerName)
}
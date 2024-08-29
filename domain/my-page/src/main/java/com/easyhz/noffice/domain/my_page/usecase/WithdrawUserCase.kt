package com.easyhz.noffice.domain.my_page.usecase

import android.content.Context
import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.data.auth.repository.auth.AuthRepository
import javax.inject.Inject

class WithdrawUserCase @Inject constructor(
    private val authRepository: AuthRepository
): BaseUseCase<Context, Unit>() {
    override suspend fun invoke(param: Context): Result<Unit> {
        return authRepository.withdraw(param)
    }
}
package com.easyhz.noffice.domain.my_page.usecase

import android.content.Context
import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.data.auth.repository.auth.AuthRepository
import com.easyhz.noffice.domain.notification.usecase.DeleteMessagingTokenUseCase
import javax.inject.Inject

class WithdrawUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val deleteMessagingTokenUseCase: DeleteMessagingTokenUseCase
): BaseUseCase<Context, Unit>() {
    override suspend fun invoke(param: Context): Result<Unit> = runCatching {
        deleteMessagingToken()
        authRepository.withdraw(param)
    }

    private suspend fun deleteMessagingToken() {
        deleteMessagingTokenUseCase(Unit).onFailure { e ->
            errorLogging(this.javaClass.name, "deleteMessagingToken", e)
            throw e
        }
    }
}
package com.easyhz.noffice.domain.home.usecase.onboarding

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.data.member.repository.user.UserRepository
import javax.inject.Inject

class SetIsFirstRunUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase<Boolean, Unit>() {
    override suspend fun invoke(param: Boolean): Result<Unit> {
        return userRepository.setIsFirstRun(param)
    }
}
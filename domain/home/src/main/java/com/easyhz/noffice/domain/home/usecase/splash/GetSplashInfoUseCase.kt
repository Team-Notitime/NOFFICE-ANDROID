package com.easyhz.noffice.domain.home.usecase.splash

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.core.model.splash.EnterScreenType
import com.easyhz.noffice.data.member.repository.user.UserRepository
import javax.inject.Inject

class GetSplashInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase<Unit, EnterScreenType>() {
    override suspend fun invoke(param: Unit): Result<EnterScreenType> = runCatching {
        userRepository.getIsFirstRun().getOrElse {
            errorLogging(this.javaClass.name, "Error getting isFirstRun", it)
            return@runCatching EnterScreenType.ONBOARDING
        }.takeIf { it }?.let {
            return@runCatching EnterScreenType.ONBOARDING
        }

        userRepository.getMemberId().getOrElse {
            errorLogging(this.javaClass.name, "Error getting memberId", it)
            return@runCatching EnterScreenType.LOGIN
        }

        userRepository.getMemberName().getOrElse {
            errorLogging(this.javaClass.name, "Error getting memberName", it)
            return@runCatching EnterScreenType.SIGN_UP
        }

        EnterScreenType.HOME
    }
}
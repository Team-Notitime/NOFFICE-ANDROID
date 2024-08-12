package com.easyhz.noffice.domain.home.usecase.splash

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.splash.EnterScreenType
import com.easyhz.noffice.data.auth.repository.token.TokenRepository
import com.easyhz.noffice.data.member.repository.user.UserRepository
import javax.inject.Inject

class GetSplashInfoUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val userRepository: UserRepository
): BaseUseCase<Unit, EnterScreenType>() {
    override suspend fun invoke(param: Unit): Result<EnterScreenType> = runCatching {
        userRepository.getIsFirstRun().getOrElse {
            return@runCatching EnterScreenType.ONBOARDING
        }

        tokenRepository.getAccessToken().getOrElse {
            return@runCatching EnterScreenType.LOGIN
        }

        return Result.success(EnterScreenType.HOME)
    }
}
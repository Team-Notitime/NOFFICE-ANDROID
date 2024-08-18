package com.easyhz.noffice.domain.home.usecase.member

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.auth.UserInfo
import com.easyhz.noffice.data.member.repository.user.UserRepository
import javax.inject.Inject

class FetchUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase<Unit, UserInfo>() {
    override suspend fun invoke(param: Unit): Result<UserInfo> {
        return userRepository.fetchUserInfo()
    }
}
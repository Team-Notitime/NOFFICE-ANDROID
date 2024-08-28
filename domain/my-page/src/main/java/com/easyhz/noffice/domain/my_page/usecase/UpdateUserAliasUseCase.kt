package com.easyhz.noffice.domain.my_page.usecase

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.data.member.repository.user.UserRepository
import javax.inject.Inject

class UpdateUserAliasUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase<String, Unit>() {
    override suspend fun invoke(param: String): Result<Unit> {
        return userRepository.updateUserAlias(param)
    }
}
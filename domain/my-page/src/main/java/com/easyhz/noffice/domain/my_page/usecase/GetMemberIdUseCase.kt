package com.easyhz.noffice.domain.my_page.usecase

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.data.member.repository.user.UserRepository
import javax.inject.Inject

class GetMemberIdUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase<Unit, Int>() {

    override suspend fun invoke(param: Unit): Result<Int> {
        return userRepository.getMemberId()
    }
}
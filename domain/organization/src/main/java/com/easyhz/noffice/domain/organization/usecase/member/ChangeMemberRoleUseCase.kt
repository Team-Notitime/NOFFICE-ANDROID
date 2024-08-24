package com.easyhz.noffice.domain.organization.usecase.member

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.organization.param.RegisterMemberParam
import com.easyhz.noffice.data.organization.repository.member.OrganizationMemberRepository
import javax.inject.Inject

class ChangeMemberRoleUseCase @Inject constructor(
    private val organizationMemberRepository: OrganizationMemberRepository
): BaseUseCase<RegisterMemberParam, Unit>() {
    override suspend fun invoke(param: RegisterMemberParam): Result<Unit> {
        return organizationMemberRepository.changeMemberRole(param)
    }
}
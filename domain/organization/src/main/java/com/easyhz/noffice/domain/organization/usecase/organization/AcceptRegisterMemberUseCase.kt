package com.easyhz.noffice.domain.organization.usecase.organization

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.organization.param.RegisterMemberParam
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepository
import javax.inject.Inject

class AcceptRegisterMemberUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
): BaseUseCase<RegisterMemberParam, Unit>() {
    override suspend fun invoke(param: RegisterMemberParam): Result<Unit> {
        return organizationRepository.acceptRegisterMember(param)
    }
}
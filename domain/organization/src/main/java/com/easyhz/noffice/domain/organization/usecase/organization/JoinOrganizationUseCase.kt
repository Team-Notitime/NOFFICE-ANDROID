package com.easyhz.noffice.domain.organization.usecase.organization

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.organization.OrganizationJoin
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepository
import javax.inject.Inject

class JoinOrganizationUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
): BaseUseCase<Int, OrganizationJoin>() {
    override suspend fun invoke(param: Int): Result<OrganizationJoin> {
        return organizationRepository.joinOrganization(param)
    }
}
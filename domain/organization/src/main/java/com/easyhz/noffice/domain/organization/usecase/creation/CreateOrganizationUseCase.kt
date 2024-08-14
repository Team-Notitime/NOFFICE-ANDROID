package com.easyhz.noffice.domain.organization.usecase.creation

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.organization.Organization
import com.easyhz.noffice.core.model.organization.param.OrganizationCreationParam
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepository
import javax.inject.Inject

class CreateOrganizationUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
): BaseUseCase<OrganizationCreationParam, Organization>() {
    override suspend fun invoke(param: OrganizationCreationParam): Result<Organization> {
        return organizationRepository.createOrganization(param)
    }
}
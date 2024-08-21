package com.easyhz.noffice.domain.organization.usecase.organization

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.organization.OrganizationSignUpInformation
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepository
import javax.inject.Inject

class FetchOrganizationSignUpInfoUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
):BaseUseCase<Int, OrganizationSignUpInformation>() {
    override suspend fun invoke(param: Int): Result<OrganizationSignUpInformation> {
        return organizationRepository.fetchOrganizationSignUpInfo(param)
    }
}
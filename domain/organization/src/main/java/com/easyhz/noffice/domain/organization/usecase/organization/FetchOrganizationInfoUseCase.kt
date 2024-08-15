package com.easyhz.noffice.domain.organization.usecase.organization

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepository
import javax.inject.Inject

class FetchOrganizationInfoUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
): BaseUseCase<Int, OrganizationInformation>() {
    override suspend fun invoke(param: Int): Result<OrganizationInformation> {
        return organizationRepository.fetchOrganizationInfo(param)
    }
}
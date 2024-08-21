package com.easyhz.noffice.domain.organization.usecase.organization

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.common.Member
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepository
import javax.inject.Inject

class FetchOrganizationPendingMembersUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
): BaseUseCase<Int, List<Member>>() {
    override suspend fun invoke(param: Int): Result<List<Member>> {
        return organizationRepository.fetchOrganizationPendingMembers(param)
    }
}
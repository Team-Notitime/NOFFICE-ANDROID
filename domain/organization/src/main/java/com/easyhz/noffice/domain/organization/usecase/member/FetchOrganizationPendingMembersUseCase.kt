package com.easyhz.noffice.domain.organization.usecase.member

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.common.Member
import com.easyhz.noffice.data.organization.repository.member.OrganizationMemberRepository
import javax.inject.Inject

class FetchOrganizationPendingMembersUseCase @Inject constructor(
    private val organizationMemberRepository: OrganizationMemberRepository
): BaseUseCase<Int, List<Member>>() {
    override suspend fun invoke(param: Int): Result<List<Member>> {
        return organizationMemberRepository.fetchOrganizationPendingMembers(param)
    }
}
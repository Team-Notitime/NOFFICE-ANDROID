package com.easyhz.noffice.data.organization.repository.member

import com.easyhz.noffice.core.model.common.Member
import com.easyhz.noffice.core.model.organization.param.RegisterMemberParam

interface OrganizationMemberRepository {
    suspend fun fetchOrganizationPendingMembers(organizationId: Int): Result<List<Member>>
    suspend fun acceptRegisterMember(param: RegisterMemberParam): Result<Unit>
}
package com.easyhz.noffice.data.organization.repository.member

import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers
import com.easyhz.noffice.core.model.common.Member
import com.easyhz.noffice.core.model.organization.param.RegisterMemberParam
import com.easyhz.noffice.core.network.api.organization.OrganizationMemberService
import com.easyhz.noffice.core.network.util.toResult
import com.easyhz.noffice.data.announcement.mapper.announcement.toModel
import com.easyhz.noffice.data.organization.mapper.toRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrganizationMemberRepositoryImpl @Inject constructor(
    @Dispatcher(NofficeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val organizationMemberService: OrganizationMemberService
): OrganizationMemberRepository {
    override suspend fun fetchOrganizationPendingMembers(organizationId: Int): Result<List<Member>> =
        withContext(dispatcher) {
            return@withContext organizationMemberService.fetchOrganizationPendingMembers(organizationId)
                .toResult().map { it.map { item -> item.toModel() } }
        }

    override suspend fun acceptRegisterMember(param: RegisterMemberParam): Result<Unit> =
        withContext(dispatcher) {
            return@withContext organizationMemberService.acceptRegisterMember(
                organizationId = param.organizationId,
                body = param.toRequest()
            ).toResult()
        }

    override suspend fun changeMemberRole(param: RegisterMemberParam): Result<Unit> =
        withContext(dispatcher) {
            return@withContext organizationMemberService.changeMemberRole(
                organizationId = param.organizationId,
                body = param.toRequest()
            ).toResult()
        }
}
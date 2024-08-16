package com.easyhz.noffice.data.organization.repository.organization

import androidx.paging.PagingData
import com.easyhz.noffice.core.model.organization.Organization
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.announcement.OrganizationAnnouncement
import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.core.model.organization.param.OrganizationCreationParam
import kotlinx.coroutines.flow.Flow

interface OrganizationRepository {
    suspend fun fetchOrganizations(): Flow<PagingData<Organization>>
    suspend fun createOrganization(param: OrganizationCreationParam): Result<Organization>
    suspend fun fetchOrganizationInfo(organizationId: Int): Result<OrganizationInformation>
    suspend fun fetchAnnouncementsByOrganization(organizationId: Int, memberId: Int): Flow<PagingData<OrganizationAnnouncement>>
    suspend fun updateOrganizationCategory(organizationId: Int, category: List<Int>): Result<Category>
    suspend fun joinOrganization(organizationId: Int): Result<Unit>
}
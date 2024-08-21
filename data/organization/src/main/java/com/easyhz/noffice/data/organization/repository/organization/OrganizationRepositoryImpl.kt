package com.easyhz.noffice.data.organization.repository.organization

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers
import com.easyhz.noffice.core.common.util.DateFormat
import com.easyhz.noffice.core.model.organization.Organization
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.announcement.OrganizationAnnouncement
import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.core.model.organization.param.OrganizationCreationParam
import com.easyhz.noffice.core.network.api.organization.OrganizationService
import com.easyhz.noffice.core.network.model.request.organization.CategoryRequest
import com.easyhz.noffice.core.network.util.toResult
import com.easyhz.noffice.data.announcement.mapper.announcement.toDetail
import com.easyhz.noffice.data.organization.mapper.toModel
import com.easyhz.noffice.data.organization.mapper.toRequest
import com.easyhz.noffice.data.organization.pagingsource.OrganizationAnnouncementPagingSource
import com.easyhz.noffice.data.organization.pagingsource.OrganizationPagingSource
import com.easyhz.noffice.data.organization.pagingsource.OrganizationPagingSource.Companion.PAGE_SIZE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrganizationRepositoryImpl @Inject constructor(
    @Dispatcher(NofficeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val organizationService: OrganizationService
) : OrganizationRepository {
    override suspend fun fetchOrganizations(): Flow<PagingData<Organization>> =
        withContext(dispatcher) {
            return@withContext Pager(
                config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE),
                pagingSourceFactory = {
                    OrganizationPagingSource(
                        organizationService = organizationService
                    )
                }
            ).flow.map { pagingData ->
                pagingData.map { response ->
                    response.toModel()
                }
            }
        }

    override suspend fun createOrganization(param: OrganizationCreationParam): Result<Organization> =
        withContext(dispatcher) {
            return@withContext organizationService.createOrganization(
                body = param.toRequest()
            ).toResult().map { it.toModel() }
        }

    override suspend fun fetchOrganizationInfo(organizationId: Int): Result<OrganizationInformation> =
        withContext(dispatcher) {
            return@withContext organizationService.fetchOrganizationInfo(organizationId).toResult()
                .map { it.toModel() }
        }

    override suspend fun fetchAnnouncementsByOrganization(
        organizationId: Int,
        endAtDatePattern: DateFormat.PATTERN,
    ): Flow<PagingData<OrganizationAnnouncement>> =
        Pager(
            config = PagingConfig(
                pageSize = OrganizationAnnouncementPagingSource.PAGE_SIZE,
                initialLoadSize = OrganizationAnnouncementPagingSource.PAGE_SIZE
            ),
            pagingSourceFactory = {
                OrganizationAnnouncementPagingSource(
                    organizationService = organizationService,
                    organizationId = organizationId,
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { response ->
                response.toDetail(endAtDatePattern)
            }
        }

    override suspend fun updateOrganizationCategory(
        organizationId: Int,
        category: List<Int>
    ): Result<Category> = withContext(dispatcher) {
        return@withContext organizationService.updateOrganizationCategory(
            organizationId,
            CategoryRequest(category)
        ).toResult().map { it.toModel() }
    }

    override suspend fun joinOrganization(organizationId: Int): Result<Unit> {
        TODO("Not yet implemented")
    }
}
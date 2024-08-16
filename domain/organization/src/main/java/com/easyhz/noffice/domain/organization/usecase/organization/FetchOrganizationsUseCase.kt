package com.easyhz.noffice.domain.organization.usecase.organization

import androidx.paging.PagingData
import com.easyhz.noffice.core.model.organization.Organization
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchOrganizationsUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
){
    suspend operator fun invoke(): Flow<PagingData<Organization>> {
        return organizationRepository.fetchOrganizations()
    }
}
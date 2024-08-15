package com.easyhz.noffice.domain.organization.usecase.announcement

import androidx.paging.PagingData
import com.easyhz.noffice.core.model.organization.announcement.OrganizationAnnouncement
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAnnouncementsByOrganizationUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
) {
    suspend operator fun invoke(organizationId: Int, memberId: Int): Flow<PagingData<OrganizationAnnouncement>> {
        return organizationRepository.fetchAnnouncementsByOrganization(organizationId, memberId)
    }
}
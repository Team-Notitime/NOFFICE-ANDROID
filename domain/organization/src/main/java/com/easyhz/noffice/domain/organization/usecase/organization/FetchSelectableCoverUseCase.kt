package com.easyhz.noffice.domain.organization.usecase.organization

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.organization.SelectableCover
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepository
import javax.inject.Inject

class FetchSelectableCoverUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
): BaseUseCase<Int, SelectableCover>() {
    override suspend fun invoke(param: Int): Result<SelectableCover> {
        return organizationRepository.fetchSelectableCover(param)
    }
}
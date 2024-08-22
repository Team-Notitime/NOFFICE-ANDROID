package com.easyhz.noffice.domain.organization.usecase.category

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.core.model.organization.param.CategoryParam
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepository
import javax.inject.Inject

class UpdateOrganizationCategoryUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
): BaseUseCase<CategoryParam, List<Category>>() {
    override suspend fun invoke(param: CategoryParam): Result<List<Category>> {
        return organizationRepository.updateOrganizationCategory(param.organizationId, param.categoryList)
    }
}
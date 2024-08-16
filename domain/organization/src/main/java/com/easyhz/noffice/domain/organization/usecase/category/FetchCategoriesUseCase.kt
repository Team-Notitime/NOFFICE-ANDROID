package com.easyhz.noffice.domain.organization.usecase.category

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.data.organization.repository.category.CategoryRepository
import javax.inject.Inject

class FetchCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
):BaseUseCase<Unit, List<Category>>() {
    override suspend fun invoke(param: Unit): Result<List<Category>> {
        return categoryRepository.fetchCategories()
    }
}

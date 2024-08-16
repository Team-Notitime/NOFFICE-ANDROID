package com.easyhz.noffice.data.organization.repository.category

import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers
import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.core.network.api.category.CategoryService
import com.easyhz.noffice.core.network.util.toResult
import com.easyhz.noffice.data.organization.mapper.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    @Dispatcher(NofficeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val categoryService: CategoryService
) : CategoryRepository {
    override suspend fun fetchCategories(): Result<List<Category>> =
        withContext(dispatcher) {
            return@withContext categoryService.fetchCategories().toResult().map {
                it.categories.map { item ->
                    item.toModel()
                }
            }
        }
}
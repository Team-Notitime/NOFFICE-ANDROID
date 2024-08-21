package com.easyhz.noffice.domain.organization.usecase.organization

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.data.organization.repository.category.CategoryRepository
import com.easyhz.noffice.data.organization.repository.organization.OrganizationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchOrganizationUseCase @Inject constructor(
    @Dispatcher(NofficeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val categoryRepository: CategoryRepository,
    private val organizationRepository: OrganizationRepository
) : BaseUseCase<Int, OrganizationInformation>() {
    override suspend fun invoke(param: Int): Result<OrganizationInformation> =
        withContext(dispatcher) {
            runCatching {
                val (categoriesResult, organizationInfoResult) = coroutineScope {
                    val categoriesDeferred = async { fetchCategories() }
                    val organizationInfoDeferred = async { fetchOrganizationInfo(param) }

                    categoriesDeferred.await() to organizationInfoDeferred.await()
                }

                val categoryList = categoriesResult.getOrThrow()
                val info = organizationInfoResult.getOrThrow()
                val updatedCategories = info.category.map { item ->
                    categoryList.find { it.id == item.id }?.let { matchedCategory ->
                        item.copy(title = matchedCategory.title)
                    } ?: item
                }

                info.copy(category = updatedCategories)
            }
        }


    private suspend fun fetchCategories(): Result<List<Category>> {
        return categoryRepository.fetchCategories()
    }

    private suspend fun fetchOrganizationInfo(id: Int): Result<OrganizationInformation> {
        return organizationRepository.fetchOrganizationInfo(id)
    }

}
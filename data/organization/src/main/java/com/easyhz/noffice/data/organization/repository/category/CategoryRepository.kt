package com.easyhz.noffice.data.organization.repository.category

import com.easyhz.noffice.core.model.organization.category.Category

interface CategoryRepository {
    suspend fun fetchCategories(): Result<List<Category>>
}
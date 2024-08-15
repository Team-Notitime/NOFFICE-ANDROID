package com.easyhz.noffice.data.organization.mapper

import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.core.network.model.response.category.CategoryResponse

internal fun CategoryResponse.toModel(): Category = Category(
    id = this.id,
    title = this.name,
    isSelected = false
)
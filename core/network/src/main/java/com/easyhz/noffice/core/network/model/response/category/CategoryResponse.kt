package com.easyhz.noffice.core.network.model.response.category

data class CategoryListResponse(
    val categories: List<CategoryResponse>
)

data class CategoryUpdateResponse(
    val organizationId: Int,
    val organizationName: String,
    val categories: CategoryListResponse
)

data class CategoryResponse(
    val id: Int,
    val name: String
)

package com.easyhz.noffice.core.network.model.response.category

data class CategoryListResponse(
    val categories: List<CategoryResponse>
)
data class CategoryResponse(
    val id: Int,
    val name: String
)

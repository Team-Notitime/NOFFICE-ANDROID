package com.easyhz.noffice.core.network.api.category

import com.easyhz.noffice.core.network.model.response.category.CategoryListResponse
import com.easyhz.noffice.core.network.model.response.category.CategoryResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.GET

interface CategoryService {
    @GET("/api/v1/category")
    suspend fun fetchCategories(): NofficeResult<CategoryListResponse>
}
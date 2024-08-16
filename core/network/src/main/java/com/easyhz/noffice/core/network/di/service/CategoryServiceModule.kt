package com.easyhz.noffice.core.network.di.service

import com.easyhz.noffice.core.network.api.category.CategoryService
import com.easyhz.noffice.core.network.di.NofficeRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CategoryServiceModule {
    @Provides
    fun provideCategoryService(@NofficeRetrofit retrofit: Retrofit): CategoryService =
        retrofit.create(CategoryService::class.java)
}
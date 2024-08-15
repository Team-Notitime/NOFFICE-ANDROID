package com.easyhz.noffice.data.organization.di.repository

import com.easyhz.noffice.data.organization.repository.category.CategoryRepository
import com.easyhz.noffice.data.organization.repository.category.CategoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CategoryRepositoryModule {
    @Binds
    fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository
}
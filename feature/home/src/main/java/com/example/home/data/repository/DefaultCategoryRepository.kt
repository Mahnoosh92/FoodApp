package com.example.home.data.repository

import com.example.home.data.datasource.local.LocalCategoryDataSource
import com.example.home.data.datasource.remote.RemoteCategoryDataSource
import com.example.home.data.di.IoDispatcher
import com.example.home.domain.mapper.toCategory
import com.example.home.domain.mapper.toCategoryEntity
import com.example.foodapp.domain.model.Category
import com.example.home.domain.repository.CategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultCategoryRepository @Inject constructor(
    private val localCategoryDataSource: LocalCategoryDataSource,
    private val remoteCategoryDataSource: RemoteCategoryDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CategoryRepository {
    override fun getCategories(): Flow<List<Category>> =
        localCategoryDataSource.getCategories().map {
            it.map { it.toCategory() }
        }

    override suspend fun syncCategoriesWithRemote(update: Boolean) = withContext(ioDispatcher) {
        if (update) {
            remoteCategoryDataSource
                .getCategories()
                .onSuccess {
                    localCategoryDataSource.clear()
                    localCategoryDataSource.insertAll(categoryEntities = it.map { it.toCategoryEntity() })
                }
                .onFailure {
                    throw it
                }
        }
    }
}
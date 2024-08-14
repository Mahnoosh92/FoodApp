package com.example.home.data.datasource.local


import com.example.database.data.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface LocalCategoryDataSource {

    suspend fun insert(categoryEntity: CategoryEntity)
    suspend fun insertAll(categoryEntities: List<CategoryEntity>)
    fun getCategories(): Flow<List<CategoryEntity>>
    suspend fun clear()
}
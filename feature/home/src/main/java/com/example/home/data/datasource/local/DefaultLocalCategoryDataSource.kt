package com.example.home.data.datasource.local


import com.example.database.data.db.CategoryDao
import com.example.database.data.model.CategoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultLocalCategoryDataSource @Inject constructor(private val categoryDao: CategoryDao) :
    LocalCategoryDataSource {
    override suspend fun insert(categoryEntity: CategoryEntity) = categoryDao.insert(categoryEntity)

    override suspend fun insertAll(categoryEntities: List<CategoryEntity>) =
        categoryDao.insertAll(categoryEntities)

    override fun getCategories(): Flow<List<CategoryEntity>> = categoryDao.getCategories()

    override suspend fun clear() = categoryDao.clear()
}
package com.example.home.domain.repository

import com.example.foodapp.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
    suspend fun syncCategoriesWithRemote(update: Boolean = false)
}
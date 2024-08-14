package com.example.home.domain.usecase.category

import com.example.foodapp.domain.model.Category
import com.example.home.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultGetCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) :
    GetCategoryUseCase {
    override suspend fun invoke(): Flow<List<Category>> = categoryRepository.getCategories()
}
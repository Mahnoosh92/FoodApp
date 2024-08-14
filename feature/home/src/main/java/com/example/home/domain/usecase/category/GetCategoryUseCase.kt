package com.example.home.domain.usecase.category

import com.example.foodapp.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface GetCategoryUseCase {
    suspend operator fun invoke(): Flow<List<Category>>
}
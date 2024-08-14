package com.example.home.domain.usecase.recipe

import com.example.foodapp.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface GetRecipeUseCase {
    suspend operator fun invoke(): Flow<List<Recipe>>
}
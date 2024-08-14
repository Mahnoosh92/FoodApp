package com.example.home.domain.usecase.recipe

import com.example.foodapp.domain.model.Recipe
import com.example.home.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultGetRecipeUseCase @Inject constructor(private val recipeRepository: RecipeRepository) :
    GetRecipeUseCase {
    override suspend fun invoke(): Flow<List<Recipe>> = recipeRepository.getRecipes()
}
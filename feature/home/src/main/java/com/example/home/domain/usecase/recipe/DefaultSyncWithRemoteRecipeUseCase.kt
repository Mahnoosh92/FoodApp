package com.example.home.domain.usecase.recipe

import com.example.home.domain.repository.RecipeRepository
import javax.inject.Inject

class DefaultSyncWithRemoteRecipeUseCase @Inject constructor(private val recipeRepository: RecipeRepository) :
    SyncWithRemoteRecipeUseCase {
    override suspend fun invoke(update: Boolean) = recipeRepository.syncRecipesWithRemote(update = update)
}
package com.example.home.domain.usecase.recipe

import com.example.home.domain.repository.RecipeRepository
import javax.inject.Inject

class DefaultUpdateRecipeFavouriteStatusUseCase @Inject constructor(private val recipeRepository: RecipeRepository) :
    UpdateRecipeFavouriteStatusUseCase {
    override suspend fun invoke(id: String, isFavourite: Boolean) =
        recipeRepository.updateIsFavourite(id = id, isFavourite = isFavourite)
}
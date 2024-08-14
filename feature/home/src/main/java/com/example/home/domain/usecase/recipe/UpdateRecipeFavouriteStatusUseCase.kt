package com.example.home.domain.usecase.recipe

interface UpdateRecipeFavouriteStatusUseCase {
    suspend operator fun invoke(id: String, isFavourite: Boolean)
}
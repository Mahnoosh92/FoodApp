package com.example.home.domain.usecase.recipe

interface SyncWithRemoteRecipeUseCase {
    suspend operator fun invoke(update: Boolean)

}
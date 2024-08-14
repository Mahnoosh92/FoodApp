package com.example.home.domain.repository

import com.example.foodapp.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getRecipes(): Flow<List<Recipe>>
    fun getFavouriteRecipes(): Flow<List<Recipe>>
    suspend fun syncRecipesWithRemote(update: Boolean = false)
    suspend fun updateIsFavourite(id: String, isFavourite: Boolean)
}
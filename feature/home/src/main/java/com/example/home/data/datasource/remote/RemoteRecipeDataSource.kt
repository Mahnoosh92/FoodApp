package com.example.home.data.datasource.remote

import com.example.network.data.model.RecipeDTO

interface RemoteRecipeDataSource {

    suspend fun getRecipes(): Result<List<RecipeDTO>>
    suspend fun getRecipeDetailById(recipeId: String): Result<List<RecipeDTO>>
    suspend fun searchRecipes(query: String): Result<List<RecipeDTO>>
}
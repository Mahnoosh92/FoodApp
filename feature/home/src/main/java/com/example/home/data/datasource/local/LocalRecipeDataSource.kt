package com.example.home.data.datasource.local

import com.example.database.data.model.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface LocalRecipeDataSource {
    suspend fun insert(recipeEntity: RecipeEntity)

    suspend fun insertAll(recipeEntities: List<RecipeEntity>)

    fun getRecipes(): Flow<List<RecipeEntity>>
    fun getFavouriteRecipes(): Flow<List<RecipeEntity>>

    suspend fun clear(id: Long)
    suspend fun clear()

    suspend fun update(id: String, isFavourite: Boolean)
}
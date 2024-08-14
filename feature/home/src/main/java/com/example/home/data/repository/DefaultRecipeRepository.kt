package com.example.home.data.repository

import com.example.home.data.datasource.local.LocalRecipeDataSource
import com.example.home.data.datasource.remote.RemoteRecipeDataSource
import com.example.home.data.di.IoDispatcher
import com.example.home.domain.mapper.toRecipe
import com.example.home.domain.mapper.toRecipeEntity
import com.example.foodapp.domain.model.Recipe
import com.example.home.domain.repository.RecipeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultRecipeRepository @Inject constructor(
    private val remoteRecipeDataSource: RemoteRecipeDataSource,
    private val localRecipeDataSource: LocalRecipeDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RecipeRepository {

    override fun getRecipes(): Flow<List<Recipe>> =
        localRecipeDataSource.getRecipes().map { it.map { it.toRecipe() } }

    override fun getFavouriteRecipes(): Flow<List<Recipe>> =
        localRecipeDataSource.getFavouriteRecipes().map { it.map { it.toRecipe() } }

    override suspend fun syncRecipesWithRemote(update: Boolean) = withContext(ioDispatcher) {
        if (update) {
            remoteRecipeDataSource
                .getRecipes()
                .onSuccess {
                    localRecipeDataSource.clear()
                    localRecipeDataSource.insertAll(it.map { it.toRecipeEntity() })
                }
                .onFailure {
                    throw it
                }
        }
    }

    override suspend fun updateIsFavourite(id: String, isFavourite: Boolean) =
        localRecipeDataSource.update(id = id, isFavourite = isFavourite)

}
package com.example.home.data.datasource.local

import com.example.database.data.db.RecipeDao
import com.example.database.data.model.RecipeEntity

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultLocalRecipeDataSource @Inject constructor(private val recipeDao: RecipeDao) :
    LocalRecipeDataSource {
    override suspend fun insert(recipeEntity: RecipeEntity) =
        recipeDao.insert(recipeEntity = recipeEntity)

    override suspend fun insertAll(recipeEntities: List<RecipeEntity>) =
        recipeDao.insertAll(recipeEntities = recipeEntities)

    override fun getRecipes(): Flow<List<RecipeEntity>> = recipeDao.getRecipes()

    override fun getFavouriteRecipes(): Flow<List<RecipeEntity>> = recipeDao.getFavouriteRecipes()

    override suspend fun clear(id: Long) = recipeDao.clear(id = id)

    override suspend fun clear() = recipeDao.clear()

    override suspend fun update(id: String, isFavourite: Boolean) =
        recipeDao.update(id = id, isFavourite = isFavourite)
}
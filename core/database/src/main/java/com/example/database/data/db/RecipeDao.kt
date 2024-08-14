package com.example.database.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.data.model.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipeEntity: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipeEntities: List<RecipeEntity>)

    @Query("select * from recipe_table")
    fun getRecipes(): Flow<List<RecipeEntity>>

    @Query("select * from recipe_table where isFavourite = 1")
    fun getFavouriteRecipes(): Flow<List<RecipeEntity>>

    @Query("DELETE FROM recipe_table WHERE id = :id")
    suspend fun clear(id: Long)

    @Query("DELETE FROM recipe_table")
    suspend fun clear()

    @Query("UPDATE recipe_table SET isFavourite = :isFavourite WHERE id = :id")
    suspend fun update(id: String, isFavourite: Boolean)
}
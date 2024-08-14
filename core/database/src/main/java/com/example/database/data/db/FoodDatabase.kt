package com.example.database.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.data.model.CategoryEntity
import com.example.database.data.model.RecipeEntity

@Database(
    entities = [CategoryEntity::class, RecipeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun recipeDao(): RecipeDao
}
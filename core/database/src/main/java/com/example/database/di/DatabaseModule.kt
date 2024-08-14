package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.data.db.FoodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFoodDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext, FoodDatabase::class.java, "food_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideCategoryDao(db: FoodDatabase) = db.categoryDao()

    @Provides
    @Singleton
    fun provideRecipeDao(db: FoodDatabase) = db.recipeDao()
}
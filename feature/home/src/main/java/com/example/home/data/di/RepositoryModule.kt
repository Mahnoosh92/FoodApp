package com.example.home.data.di

import com.example.home.data.repository.DefaultCategoryRepository
import com.example.home.data.repository.DefaultRecipeRepository
import com.example.home.domain.repository.CategoryRepository
import com.example.home.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindCategoryRepository(
        defaultCategoryRepository: DefaultCategoryRepository
    ): CategoryRepository

    @Binds
    @ActivityRetainedScoped
    abstract fun bindRecipeRepository(
        defaultRecipeRepository: DefaultRecipeRepository
    ): RecipeRepository
}
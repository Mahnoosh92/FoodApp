package com.example.home.data.di

import com.example.home.domain.usecase.category.DefaultGetCategoryUseCase
import com.example.home.domain.usecase.category.DefaultSyncWithRemoteCategoryUseCase
import com.example.home.domain.usecase.category.GetCategoryUseCase
import com.example.home.domain.usecase.category.SyncWithRemoteCategoryUseCase
import com.example.home.domain.usecase.recipe.DefaultGetRecipeUseCase
import com.example.home.domain.usecase.recipe.DefaultSyncWithRemoteRecipeUseCase
import com.example.home.domain.usecase.recipe.DefaultUpdateRecipeFavouriteStatusUseCase
import com.example.home.domain.usecase.recipe.GetRecipeUseCase
import com.example.home.domain.usecase.recipe.SyncWithRemoteRecipeUseCase
import com.example.home.domain.usecase.recipe.UpdateRecipeFavouriteStatusUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class UseCaseModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindGetCategoryUseCase(
        defaultGetCategoryUseCase: DefaultGetCategoryUseCase
    ): GetCategoryUseCase

    @Binds
    @ActivityRetainedScoped
    abstract fun bindUpdateCategoryUseCase(
        defaultSyncWithRemoteCategoryUseCase: DefaultSyncWithRemoteCategoryUseCase
    ): SyncWithRemoteCategoryUseCase

    @Binds
    @ActivityRetainedScoped
    abstract fun bindGetRecipeUseCase(
        defaultGetRecipeUseCase: DefaultGetRecipeUseCase
    ): GetRecipeUseCase

    @Binds
    @ActivityRetainedScoped
    abstract fun bindUpdateRecipeUseCase(
        defaultSyncWithRemoteRecipeUseCase: DefaultSyncWithRemoteRecipeUseCase
    ): SyncWithRemoteRecipeUseCase

    @Binds
    @ActivityRetainedScoped
    abstract fun bindUpdateRecipeFavouriteStatusUseCase(
        defaultUpdateRecipeFavouriteStatusUseCase: DefaultUpdateRecipeFavouriteStatusUseCase
    ): UpdateRecipeFavouriteStatusUseCase
}
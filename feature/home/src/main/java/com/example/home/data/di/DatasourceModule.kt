package com.example.home.data.di

import com.example.home.data.datasource.local.DefaultLocalCategoryDataSource
import com.example.home.data.datasource.local.DefaultLocalRecipeDataSource
import com.example.home.data.datasource.local.LocalCategoryDataSource
import com.example.home.data.datasource.local.LocalRecipeDataSource
import com.example.home.data.datasource.remote.DefaultRemoteCategoryDataSource
import com.example.home.data.datasource.remote.DefaultRemoteRecipeDataSource
import com.example.home.data.datasource.remote.RemoteCategoryDataSource
import com.example.home.data.datasource.remote.RemoteRecipeDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteRecipeDataSource(
        defaultRemoteRecipeDataSource: DefaultRemoteRecipeDataSource
    ): RemoteRecipeDataSource

    @Binds
    abstract fun bindRemoteCategoryDataSource(
        defaultRemoteCategoryDataSource: DefaultRemoteCategoryDataSource
    ): RemoteCategoryDataSource

    @Binds
    abstract fun bindLocalRecipeDataSource(
        defaultLocalRecipeDataSource: DefaultLocalRecipeDataSource
    ): LocalRecipeDataSource

    @Binds
    abstract fun bindLocalCategoryDataSource(
        defaultLocalCategoryDataSource: DefaultLocalCategoryDataSource
    ): LocalCategoryDataSource

}
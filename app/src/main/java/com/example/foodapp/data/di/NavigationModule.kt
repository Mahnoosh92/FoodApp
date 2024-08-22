package com.example.foodapp.data.di

import com.example.splash.presentation.SplashNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class NavigationModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindSplashNavigation(
        defaultSplashNavigation: DefaultSplashNavigation
    ): SplashNavigation

}
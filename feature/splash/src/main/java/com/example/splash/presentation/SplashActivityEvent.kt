package com.example.splash.presentation

sealed interface SplashActivityEvent {
    data object DefineStartDestination : SplashActivityEvent
}
package com.example.foodapp.presentation

sealed interface MainActivityEvent {
    data object DefineStartDestination : MainActivityEvent
}
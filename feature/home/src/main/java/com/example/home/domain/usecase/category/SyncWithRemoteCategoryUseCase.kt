package com.example.home.domain.usecase.category

interface SyncWithRemoteCategoryUseCase {
    suspend operator fun invoke(update: Boolean = false)
}
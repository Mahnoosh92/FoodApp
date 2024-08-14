package com.example.home.domain.usecase.category

import com.example.home.domain.repository.CategoryRepository
import javax.inject.Inject

class DefaultSyncWithRemoteCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) :
    SyncWithRemoteCategoryUseCase {
    override suspend fun invoke(update: Boolean) =
        categoryRepository.syncCategoriesWithRemote(update = update)
}
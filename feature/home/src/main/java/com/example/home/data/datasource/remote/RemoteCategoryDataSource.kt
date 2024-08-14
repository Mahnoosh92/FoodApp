package com.example.home.data.datasource.remote

import com.example.network.data.model.CategoryDTO

interface RemoteCategoryDataSource {
    suspend fun getCategories(): Result<List<CategoryDTO>>
}
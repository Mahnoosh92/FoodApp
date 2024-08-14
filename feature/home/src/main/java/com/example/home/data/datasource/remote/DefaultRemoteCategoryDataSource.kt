package com.example.home.data.datasource.remote

import com.example.home.data.di.IoDispatcher
import com.example.foodapp.domain.model.NetworkException
import com.example.network.data.api.ApiService
import com.example.network.data.model.CategoryDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class DefaultRemoteCategoryDataSource @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteCategoryDataSource {
    override suspend fun getCategories(): Result<List<CategoryDTO>> = withContext(ioDispatcher) {
        try {
            val response = apiService.getCategories()
            if (response.isSuccessful) {
                Result.success<List<CategoryDTO>>(
                    response.body()?.categories ?: emptyList<CategoryDTO>()
                )
            } else {
                Result.failure(Throwable("Something went wrong"))
            }
        } catch (e: HttpException) {
            throw NetworkException(e.message())
        }
    }
}
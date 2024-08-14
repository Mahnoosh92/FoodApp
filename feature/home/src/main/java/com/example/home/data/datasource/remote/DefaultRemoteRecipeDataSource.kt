package com.example.home.data.datasource.remote

import com.example.home.data.di.IoDispatcher
import com.example.foodapp.domain.model.NetworkException
import com.example.network.data.api.ApiService
import com.example.network.data.model.RecipeDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class DefaultRemoteRecipeDataSource @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteRecipeDataSource {
    override suspend fun getRecipes(): Result<List<RecipeDTO>> = withContext(ioDispatcher)
    {
        try {
            val response = apiService.getRecipes(randomChar = "a")
            if (response.isSuccessful) {
                Result.success<List<RecipeDTO>>(response.body()?.recipes ?: emptyList<RecipeDTO>())
            } else {
                Result.failure(Throwable("Something went wrong"))
            }
        } catch (e: HttpException) {
            throw NetworkException(e.message())
        }
    }

    override suspend fun getRecipeDetailById(recipeId: String): Result<List<RecipeDTO>> =
        withContext(ioDispatcher) {
            try {
                val response = apiService.getRecipeDetailById(recipeId)
                if (response.isSuccessful) {
                    Result.success<List<RecipeDTO>>(
                        response.body()?.recipes ?: emptyList<RecipeDTO>()
                    )
                } else {
                    Result.failure(Throwable("Something went wrong"))
                }
            } catch (e: HttpException) {
                throw NetworkException(e.message())
            }
        }

    override suspend fun searchRecipes(query: String): Result<List<RecipeDTO>> =
        withContext(ioDispatcher) {
            try {
                val response = apiService.search(query)
                if (response.isSuccessful) {
                    Result.success<List<RecipeDTO>>(
                        response.body()?.recipes ?: emptyList<RecipeDTO>()
                    )
                } else {
                    Result.failure(Throwable("Something went wrong"))
                }
            } catch (e: HttpException) {
                throw NetworkException(e.message())
            }
        }

}
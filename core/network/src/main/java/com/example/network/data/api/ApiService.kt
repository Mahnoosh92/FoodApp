package com.example.network.data.api

import com.example.network.data.model.CategoryResponseDTO
import com.example.network.data.model.RecipeResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("random.php")
    suspend fun getRecipes(@Query("s") randomChar: String): Response<RecipeResponseDTO>

    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryResponseDTO>

    @GET("lookup.php")
    suspend fun getRecipeDetailById(@Query("i") recipeId: String): Response<RecipeResponseDTO>

    @GET("search.php")
    suspend fun search(@Query("s") query: String): Response<RecipeResponseDTO>
}
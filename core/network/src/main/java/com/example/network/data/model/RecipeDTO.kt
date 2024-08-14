package com.example.network.data.model

import com.google.gson.annotations.SerializedName


data class RecipeDTO(
    @SerializedName("idMeal") val recipeId: String?,
    @SerializedName("strMeal") val title: String?,
    @SerializedName("strMealThumb") val image: String?,
    @SerializedName("strInstructions") val instructions: String?,
    @SerializedName("strTags") val tag: String?
)
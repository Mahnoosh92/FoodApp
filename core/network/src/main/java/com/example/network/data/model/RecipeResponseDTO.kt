package com.example.network.data.model

import com.example.network.data.model.RecipeDTO
import com.google.gson.annotations.SerializedName


data class RecipeResponseDTO(@SerializedName("meals") val recipes: List<RecipeDTO>)
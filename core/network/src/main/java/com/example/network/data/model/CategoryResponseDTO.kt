package com.example.network.data.model

import com.example.network.data.model.CategoryDTO
import com.google.gson.annotations.SerializedName

data class CategoryResponseDTO(@SerializedName("categories") val categories: List<CategoryDTO>)
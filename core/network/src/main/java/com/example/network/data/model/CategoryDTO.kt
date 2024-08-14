package com.example.network.data.model

import com.google.gson.annotations.SerializedName


data class CategoryDTO(
    val id: Long?,
    @SerializedName("strCategory") val title: String,
    @SerializedName("idCategory") val categoryId: String,
    @SerializedName("strCategoryThumb") val image: String?,
    @SerializedName("strCategoryDescription") val description: String?
)
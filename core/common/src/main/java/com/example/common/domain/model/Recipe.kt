package com.example.foodapp.domain.model

data class Recipe(
    val recipeId: String?,
    val title: String?,
    val image: String?,
    val instructions: String?,
    val tag: String?,
    val isFavourite: Boolean
)

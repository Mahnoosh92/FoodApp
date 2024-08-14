package com.example.home.domain.mapper

import com.example.database.data.model.CategoryEntity
import com.example.database.data.model.RecipeEntity
import com.example.foodapp.domain.model.Category
import com.example.foodapp.domain.model.Recipe
import com.example.network.data.model.CategoryDTO
import com.example.network.data.model.RecipeDTO

fun Category.toCategoryEntity() = CategoryEntity(
    title = this.title,
    categoryId = this.categoryId,
    image = this.image,
    description = this.description
)

fun CategoryEntity.toCategory() = Category(
    title = this.title,
    categoryId = this.categoryId,
    image = this.image,
    description = this.description
)

fun CategoryDTO.toCategoryEntity() = CategoryEntity(
    title = this.title,
    categoryId = this.categoryId,
    image = this.image,
    description = this.description
)


fun Recipe.toRecipeEntity() = RecipeEntity(
    recipeId = this.recipeId,
    title = this.title,
    image = this.image,
    instructions = this.instructions,
    tag = this.tag,
    isFavourite = this.isFavourite
)

fun RecipeEntity.toRecipe() = Recipe(
    recipeId = this.recipeId,
    title = this.title,
    image = this.image,
    instructions = this.instructions,
    tag = this.tag,
    isFavourite = this.isFavourite
)

fun RecipeDTO.toRecipeEntity() = RecipeEntity(
    recipeId = this.recipeId,
    title = this.title,
    image = this.image,
    instructions = this.instructions,
    tag = this.tag
)
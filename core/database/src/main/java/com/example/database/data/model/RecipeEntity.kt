package com.example.database.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recipe_table")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo val recipeId: String?,
    @ColumnInfo val title: String?,
    @ColumnInfo val image: String?,
    @ColumnInfo val instructions: String?,
    @ColumnInfo val tag: String?,
    @ColumnInfo val isFavourite: Boolean = false
)

package com.example.database.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo val title: String?,
    @ColumnInfo val categoryId: String?,
    @ColumnInfo val image: String?,
    @ColumnInfo val description: String?
)

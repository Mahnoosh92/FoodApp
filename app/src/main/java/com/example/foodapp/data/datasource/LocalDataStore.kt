package com.example.foodapp.data.datasource

import androidx.datastore.preferences.core.Preferences

interface LocalDataStore {
    suspend fun saveBooleanData(data: Boolean, key: String): Result<Preferences>
    suspend fun getBooleanData(key:String): Result<Boolean?>
}
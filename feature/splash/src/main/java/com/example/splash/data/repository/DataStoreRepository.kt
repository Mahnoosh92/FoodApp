package com.example.splash.data.repository

import androidx.datastore.preferences.core.Preferences

interface DataStoreRepository {
    suspend fun saveBooleanData(data: Boolean, key: String): Result<Preferences>
    suspend fun getBooleanData(key:String): Result<Boolean?>
}
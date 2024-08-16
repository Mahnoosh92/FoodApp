package com.example.foodapp.data.repository

import com.example.foodapp.data.datasource.LocalDataStore
import javax.inject.Inject

class DefaultDataStoreRepository @Inject constructor(private val localDataStore: LocalDataStore) :
    DataStoreRepository {
    override suspend fun saveBooleanData(data: Boolean, key: String) =
        localDataStore.saveBooleanData(data = data, key = key)

    override suspend fun getBooleanData(key: String): Result<Boolean?> =
        localDataStore.getBooleanData(key = key)
}
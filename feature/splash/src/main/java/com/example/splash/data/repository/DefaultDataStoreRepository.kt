package com.example.splash.data.repository

import com.example.splash.data.datasource.LocalDataStore
import com.example.splash.data.repository.DataStoreRepository
import javax.inject.Inject

class DefaultDataStoreRepository @Inject constructor(private val localDataStore: LocalDataStore) :
    DataStoreRepository {
    override suspend fun saveBooleanData(data: Boolean, key: String) =
        localDataStore.saveBooleanData(data = data, key = key)

    override suspend fun getBooleanData(key: String): Result<Boolean?> =
        localDataStore.getBooleanData(key = key)
}
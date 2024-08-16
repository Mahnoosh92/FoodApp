package com.example.foodapp.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.home.data.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultLocalDataStore @Inject constructor(
    private val userDataStorePreferences: DataStore<Preferences>,
    @IoDispatcher private val iosDispatcher: CoroutineDispatcher
) :
    LocalDataStore {
    override suspend fun saveBooleanData(data: Boolean, key: String) = withContext(iosDispatcher) {
        kotlin.runCatching {
            userDataStorePreferences.edit { preferences ->
                preferences[booleanPreferencesKey(key)] = data
            }
        }
    }

    override suspend fun getBooleanData(key: String) = withContext(iosDispatcher) {
        kotlin.runCatching {
            userDataStorePreferences
                .data
                .catch {
                    throw it
                }
                .map { preferences ->
                    // Get our name value, defaulting to "" if not set
                    preferences[booleanPreferencesKey(key)]
                }
                .firstOrNull()
        }
    }

}
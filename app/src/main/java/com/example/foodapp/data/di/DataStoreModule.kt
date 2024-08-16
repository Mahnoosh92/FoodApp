package com.example.foodapp.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.foodapp.data.datasource.DefaultLocalDataStore
import com.example.foodapp.data.datasource.LocalDataStore
import com.example.foodapp.data.repository.DataStoreRepository
import com.example.foodapp.data.repository.DefaultDataStoreRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton
private const val USER_PREFERENCES = "user_preferences"

const val DATASTORE_NAME = "user_preferences"
val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    // just my preference of naming including the package name
    name = DATASTORE_NAME
)
@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {
    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(appContext,USER_PREFERENCES)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindLocalDataStore(
        defaultLocalDataStore: DefaultLocalDataStore
    ): LocalDataStore

}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataRepositoryModule {

    @Binds
    abstract fun bindDataStoreRepository(
        defaultDataStoreRepository: DefaultDataStoreRepository
    ): DataStoreRepository

}
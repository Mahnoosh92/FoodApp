package com.example.foodapp.remote

import com.example.foodapp.data.api.ApiService
import com.example.foodapp.data.datasource.remote.DefaultRemoteRecipeDataSource
import com.example.foodapp.data.datasource.remote.RemoteRecipeDataSource
import com.example.foodapp.data.di.IoDispatcher
import com.example.foodapp.utils.FakeServer
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import javax.inject.Inject

@HiltAndroidTest
class DefaultRemoteRecipeDataSourceTest {

    @get:Rule // 2
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var retrofitBuilder: Retrofit.Builder

    @Inject
    @IoDispatcher
    lateinit var ioDispatcher: CoroutineDispatcher

    private val fakeServer = FakeServer()
    private lateinit var api: ApiService

    private lateinit var remoteRecipeDataSource: RemoteRecipeDataSource


    @Before
    fun setUp() {
        fakeServer.start()

        hiltRule.inject()

        api = retrofitBuilder
            .baseUrl(fakeServer.baseEndpoint)
            .build()
            .create(ApiService::class.java)

        remoteRecipeDataSource = DefaultRemoteRecipeDataSource(apiService = api, ioDispatcher)
    }

    @After
    fun tearDown() {
        fakeServer.shutdown()
    }

    @Test
    fun shouldReturnRecipes() = runBlocking {
        fakeServer.setHappyPathDispatcher()

        val result = remoteRecipeDataSource.getRecipes()

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()?.size).isEqualTo(1)

    }

}
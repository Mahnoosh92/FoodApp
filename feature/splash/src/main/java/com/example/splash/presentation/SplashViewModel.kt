package com.example.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splash.data.repository.DataStoreRepository
import com.example.threading.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    companion object {
        private const val ONBOARDING_KEY = "onboarding"
    }

    private var _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(mainActivityEvent: SplashActivityEvent) {
        when (mainActivityEvent) {
            is SplashActivityEvent.DefineStartDestination -> defineStartDestination()
        }
    }

    private fun defineStartDestination() {
        viewModelScope.launch(mainDispatcher) {
            dataStoreRepository.getBooleanData(ONBOARDING_KEY)
                .onSuccess {
                    it?.let { onBoarding ->
                        if (onBoarding) {
                            _uiState.update { oldState ->
                                oldState.copy(
                                    isOnBoardingShown = true
                                )
                            }
                        }
                    } ?: kotlin.run {
                        _uiState.update {
                            it.copy(
                                isOnBoardingShown = false
                            )
                        }
                    }
                }
                .onFailure {

                }
        }
    }

    fun saveObBoardingToDataStore() {
        viewModelScope.launch {
            dataStoreRepository.saveBooleanData(true, ONBOARDING_KEY)
        }
    }
}

data class SplashUiState(val isOnBoardingShown: Boolean? = null)
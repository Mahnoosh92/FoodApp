package com.example.foodapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.repository.DataStoreRepository
import com.example.home.data.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) :
    ViewModel() {


    companion object {
        private const val ONBOARDING_KEY = "onboarding"
    }

    private var _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(mainActivityEvent: MainActivityEvent) {
        when (mainActivityEvent) {
            is MainActivityEvent.DefineStartDestination -> defineStartDestination()
        }
    }

    private fun defineStartDestination() {
        viewModelScope.launch(mainDispatcher) {
            dataStoreRepository.getBooleanData(ONBOARDING_KEY)
                .onSuccess {
                    it?.let { onBoarding ->
                        if (onBoarding) {
                            _uiState.update {
                                it.copy(
                                    startDestination = com.example.home.R.id.home_navigation
                                )
                            }
                        }
                    } ?: kotlin.run {
                        _uiState.update {
                            it.copy(
                                startDestination = com.example.onboarding.R.id.onboarding_naviggation
                            )
                        }
                        dataStoreRepository.saveBooleanData(true, ONBOARDING_KEY)
                    }
                }
                .onFailure {

                }
        }
    }

}
package com.example.home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.domain.model.Event
import com.example.foodapp.domain.model.Category
import com.example.foodapp.domain.model.NetworkException
import com.example.foodapp.domain.model.Recipe
import com.example.home.data.di.MainDispatcher
import com.example.home.domain.usecase.category.GetCategoryUseCase
import com.example.home.domain.usecase.category.SyncWithRemoteCategoryUseCase
import com.example.home.domain.usecase.recipe.GetRecipeUseCase
import com.example.home.domain.usecase.recipe.SyncWithRemoteRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val updateCategoryUseCase: SyncWithRemoteCategoryUseCase,
    private val getRecipeUseCase: GetRecipeUseCase,
    private val updateRecipeUseCase: SyncWithRemoteRecipeUseCase,
    private @MainDispatcher val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    val handler = CoroutineExceptionHandler { _, throwable ->
        onFailure(failure = throwable)
    }

    fun onEvent(event: GetFoodsEvent) {
        when (event) {
            is GetFoodsEvent.GetFoods -> {
                setLoading(true)
                viewModelScope.launch(mainDispatcher) {

                    updateCategoryUseCase(update = true)
                    updateRecipeUseCase(update = true)

                    getCategoryUseCase()
                        .flatMapLatest { categories ->
                            _state.update { oldState ->
                                oldState.copy(categories = categories)
                            }
                            getRecipeUseCase()
                        }
                        .catch {
                            onFailure(failure = it)
                        }
                        .collect {
                            _state.update { oldState ->
                                oldState.copy(recipes = it)
                            }
                        }
                }
                setLoading(false)
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        _state.update { oldState ->
            oldState.copy(loading = loading)
        }
    }

    private fun onFailure(failure: Throwable) {
        when (failure) {
            is NetworkException -> {
                _state.update { oldState ->
                    oldState.copy(loading = false, failure = Event(failure))
                }
            }

            else -> {
                _state.update { oldState ->
                    oldState.copy(loading = false, failure = Event(failure))
                }
            }
        }
    }
}

data class HomeUiState(
    val loading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val categories: List<Category> = emptyList(),
    val failure: Event<Throwable>? = null
)




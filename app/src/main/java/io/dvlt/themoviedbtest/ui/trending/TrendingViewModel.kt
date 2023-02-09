package io.dvlt.themoviedbtest.ui.trending

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.aymenjegham.core.helper.Response
import com.aymenjegham.core.usecase.getTrendingMovies.GetTrendingMoviesUseCase
import com.aymenjegham.framework.di.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class TrendingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {


    //to safely handle process death
    @OptIn(SavedStateHandleSaveableApi::class)
    var trendingMoviesUiState by savedStateHandle.saveable {
        mutableStateOf(TrendingScreenState())
    }
        private set

    init {
        fetchTrendingMovies()
    }

     fun fetchTrendingMovies() {
        viewModelScope.launch(dispatchers.io) {
            getTrendingMoviesUseCase()
                .collectLatest { response ->
                    when (response.status) {
                        Response.Status.SUCCESS -> {
                            response.data?.let { movies ->
                                withContext(dispatchers.mainImmediate) {
                                    trendingMoviesUiState =
                                        trendingMoviesUiState.copy(movies = movies, showLoader = false,error = null)
                                }

                            }
                        }
                        Response.Status.ERROR -> {
                            response.message?.let { errorMessage ->
                                withContext(dispatchers.mainImmediate) {
                                    trendingMoviesUiState =
                                        trendingMoviesUiState.copy(
                                            error = errorMessage,
                                            showLoader = false
                                        )
                                }
                            }
                        }
                        Response.Status.LOADING -> {
                            withContext(dispatchers.mainImmediate) {
                                trendingMoviesUiState =
                                    trendingMoviesUiState.copy(showLoader = true)
                            }
                        }
                    }
                }
        }
    }

}
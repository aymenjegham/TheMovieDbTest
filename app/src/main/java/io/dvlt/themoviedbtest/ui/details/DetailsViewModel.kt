package io.dvlt.themoviedbtest.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.aymenjegham.core.helper.Response
import com.aymenjegham.core.usecase.getMovieDetails.GetMovieDetailsUseCase
import com.aymenjegham.framework.di.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dispatchers: DispatcherProvider,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {

    //to safely handle process death
    @OptIn(SavedStateHandleSaveableApi::class)
    var movieDetailsUiState by savedStateHandle.saveable {
        mutableStateOf(MovieDetailsScreenState())
    }
        private set


    fun fetchMovieDetails(movieId: String) {
        viewModelScope.launch(dispatchers.io) {
            getMovieDetailsUseCase(movieId).collectLatest { response ->
                when (response.status) {
                    Response.Status.SUCCESS -> {
                        response.data?.let { movieDetails ->
                            withContext(dispatchers.mainImmediate) {
                                movieDetailsUiState =
                                    movieDetailsUiState.copy(
                                        movieDetails = movieDetails,
                                        showLoader = false,
                                        error = null
                                    )
                            }

                        }
                    }
                    Response.Status.ERROR -> {
                        response.message?.let { errorMessage ->
                            withContext(dispatchers.mainImmediate) {
                                movieDetailsUiState =
                                    movieDetailsUiState.copy(
                                        error = errorMessage,
                                        showLoader = false
                                    )
                            }
                        }
                    }
                    Response.Status.LOADING -> {
                        withContext(dispatchers.mainImmediate) {
                            movieDetailsUiState =
                                movieDetailsUiState.copy(showLoader = true, error = null)
                        }
                    }
                }

            }
        }
    }

}
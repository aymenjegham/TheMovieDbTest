package io.dvlt.themoviedbtest.ui.topRated

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.aymenjegham.core.domain.movie.Movie
import com.aymenjegham.core.usecase.getTopRatedMovies.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
) : ViewModel() {

    fun fetchTopRatedMovies(): Flow<PagingData<Movie>> =
        getTopRatedMoviesUseCase()

}
package com.aymenjegham.core.usecase.getTopRatedMovies


import androidx.paging.PagingData
import com.aymenjegham.core.domain.movie.Movie
import kotlinx.coroutines.flow.Flow

interface GetTopRatedMoviesUseCase {

    operator fun invoke(): Flow<PagingData<Movie>>
}